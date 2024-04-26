import zipfile
import os
import sys

# Nome do arquivo que você deseja compactar
arquivo = "lambda.py"

# Nome do arquivo zip que será criado
arquivo_zip = "lambda.zip"

# Cria um novo arquivo zip
with zipfile.ZipFile(arquivo_zip, 'w', zipfile.ZIP_DEFLATED) as zipf:
    # Adiciona o arquivo ao arquivo zip
    zipf.write(arquivo)