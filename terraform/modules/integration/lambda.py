import os
import re
import json
import mysql.connector


def lambda_handler(event, context):
    cpf = event['authorizationToken']
    print('received token: {}'.format(cpf))

    try:
        # Check if the CPF is valid
        if validate_cpf(cpf):
            # Check if CPF is in the Database
            user = get_user_by_cpf(cpf)
            if user:
                response = generatePolicy(user, 'Allow', event['methodArn'])
                return json.loads(response)
            
        raise Exception('Unauthorized')   
    
    except BaseException:
        print('unauthorized')
        return 'unauthorized'  # Return a 500 error


def validate_cpf(cpf):
    # Remove some unwanted characters
    cpf = ''.join(re.findall('\d', str(cpf)))

    # Verifies if the CPF number has 11 digits
    if (not cpf) or (len(cpf) < 11):
        return False

    # Check if it's a sequence of equal numbers, which is invalid
    if cpf == cpf[0] * len(cpf):
        return False

    # Validate 1st digit
    sum_of_products = sum((i+1) * int(cpf[i]) for i in range(9))
    expected_digit = (sum_of_products * 10 % 11) % 10
    if int(cpf[9]) != expected_digit:
        return False

    # Validate 2nd digit
    sum_of_products = sum((i+2) * int(cpf[i]) for i in range(10))
    expected_digit = (sum_of_products * 10 % 11) % 10
    if int(cpf[10]) != expected_digit:
        return False

    return True


def get_user_by_cpf(cpf):
    # Create a connection to the database
    cnx = mysql.connector.connect(
        user=os.getenv('DB_USERNAME'),
        password=os.getenv('DB_PASSWORD'),
        host=os.getenv('DB_HOST'),
        database=os.getenv('DB_NAME'))

    cursor = cnx.cursor()
    query = ("SELECT name, cpf, email FROM user WHERE cpf = %s")
    cursor.execute(query, (cpf,))
    result = cursor.fetchone()
    cursor.close()
    cnx.close()
    return result


def generatePolicy(user, effect, resource):
    authResponse = {}
    authResponse['principalId'] = user['name']
    if (effect and resource):
        policyDocument = {}
        policyDocument['Version'] = '2012-10-17'
        policyDocument['Statement'] = []
        statementOne = {}
        statementOne['Action'] = 'execute-api:Invoke'
        statementOne['Effect'] = effect
        statementOne['Resource'] = resource
        policyDocument['Statement'] = [statementOne]
        authResponse['policyDocument'] = policyDocument
    authResponse['context'] = {
        "name": user['name'],
        "cpf": user['cpf']
    }
    authResponse_JSON = json.dumps(authResponse)
    return authResponse_JSON