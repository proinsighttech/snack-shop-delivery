package com.proinsight.api.security;

import com.proinsight.api.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SnackShopLinks {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("snackShopId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("receivedDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("receivedDateEnd", TemplateVariable.VariableType.REQUEST_PARAM));
        String urlOrders = linkTo(OrderController.class).toUri().toString();
        return Link.of(UriTemplate.of(urlOrders,
        PAGINATION_VARIABLES.concat(filterVariables)).toString(), rel);
    }

    public Link linkToOrderReady(Long orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .ready(orderCode)).withRel(rel);
    }

    public Link linkToOrderDelivery(Long orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .delivery(orderCode)).withRel(rel);
    }

    public Link linkToOrderConfirm(Long orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .confirm(orderCode)).withRel(rel);
    }

    public Link linkToOrderCancel(Long orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .cancel(orderCode)).withRel(rel);
    }

    public Link linkToSnackShop(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopController.class)
                .find(snackShopId)).withRel(rel);
    }

    public Link linkToSnackShop(Long snackShopId) {
        return linkToSnackShop(snackShopId, IanaLinkRelations.SELF.value());
    }

    public Link linkToSnackShops(String rel) {
        return linkTo(methodOn(SnackShopController.class)
                .list()).withRel(rel);
    }

    public Link linkToSnackShops() {
        return linkToSnackShops(IanaLinkRelations.SELF.value());
    }

    public Link linkToSnackShopPaymentMethods(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopPaymentMethodsController.class)
                .list(snackShopId)).withRel(rel);
    }

    public Link linkToSnackShopPaymentMethods(Long snackShopId) {
        return linkToSnackShopPaymentMethods(snackShopId, IanaLinkRelations.SELF.value());
    }

    public Link linkToSnackShopRemovePaymentMethod(
            Long snackShopId, Long paymentMethodId, String rel) {

        return linkTo(methodOn(SnackShopPaymentMethodsController.class)
                .removePaymentMethod(snackShopId, paymentMethodId)).withRel(rel);
    }

    public Link linkToSnackShopAddPaymentMethod(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopPaymentMethodsController.class)
                .addPaymentMethod(snackShopId, null)).withRel(rel);
    }

    public Link linkToSnackShopInactive(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopController.class)
                .inactiveSnackShop(snackShopId)).withRel(rel);
    }

    public Link linkToSnackShopActive(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopController.class)
                .activeSnackShop(snackShopId)).withRel(rel);
    }

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .findUser(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsers(String rel) {
        return linkTo(UserController.class).withRel(rel);
    }

    public Link linkToUsers() {
        return linkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link linkToUserAddGroup(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .addGroup(userId, null)).withRel(rel);
    }

    public Link linkToUserRemoveGroup(Long userId, Long groupId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .removeGroup(userId, groupId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .listAllGroup(userId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId) {
        return linkToUserGroups(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroups(String rel) {
        return linkTo(GroupController.class).withRel(rel);
    }

    public Link linkToGroups() {
        return linkToGroups(IanaLinkRelations.SELF.value());
    }

    public Link linkToPermissions(String rel) {
        return linkTo(PermissionController.class).withRel(rel);
    }

    public Link linkToPermissions() {
        return linkToPermissions(IanaLinkRelations.SELF.value());
    }

    public Link linkToPermissionsGroup(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .list(groupId)).withRel(rel);
    }

    public Link linkToPermissionsGroup(Long groupId) {
        return linkToPermissionsGroup(groupId, IanaLinkRelations.SELF.value());
    }

    public Link linkToAddPermissionsGroup(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .addPermission(groupId, null)).withRel(rel);
    }

    public Link linkToRemovePermissionsGroup(Long groupId, Long permissionId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .removePermission(groupId, permissionId)).withRel(rel);
    }

    public Link linkToSnackShopAdmins(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopAdminUserController.class)
                .list(snackShopId)).withRel(rel);
    }

    public Link linkToSnackShopAdmins(Long snackShopId) {
        return linkToSnackShopAdmins(snackShopId, IanaLinkRelations.SELF.value());
    }

    public Link linkToSnackShopRemoveAdmin(
            Long snackShopId, Long userId, String rel) {

        return linkTo(methodOn(SnackShopAdminUserController.class)
                .removeAdmin(snackShopId, userId)).withRel(rel);
    }

    public Link linkToSnackShopAddAdmin(Long snackShopId, String rel) {
        return linkTo(methodOn(SnackShopAdminUserController.class)
                .addAdmin(snackShopId, null)).withRel(rel);
    }

    public Link linkToPaymentMethods(Long paymentMethods, String rel) {
        return linkTo(methodOn(PaymentMethodController.class)
                .find(paymentMethods, null)).withRel(rel);
    }

    public Link linkToPaymentMethods(Long paymentMethods) {
        return linkToPaymentMethods(paymentMethods, IanaLinkRelations.SELF.value());
    }

    public Link linkToPaymentMethods(String rel) {
        return linkTo(PaymentMethodController.class).withRel(rel);
    }

    public Link linkToPaymentMethods() {
        return linkToPaymentMethods(IanaLinkRelations.SELF.value());
    }

    public Link linkToProduct(Long snackShopId, Long productId, String rel) {
        return linkTo(methodOn(ProductController.class)
                .findProduct(snackShopId, productId))
                .withRel(rel);
    }

    public Link linkToProduct(Long snackShopId, Long productId) {
        return linkToProduct(snackShopId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProducts(String rel) {
            TemplateVariables filterVariables = new TemplateVariables(
                    new TemplateVariable("snackShop", TemplateVariable.VariableType.REQUEST_PARAM),
                    new TemplateVariable("category", TemplateVariable.VariableType.REQUEST_PARAM));

            String urlProducts = linkTo(ProductController.class).toUri().toString();
            return Link.of(UriTemplate.of(urlProducts,
                    PAGINATION_VARIABLES.concat(filterVariables)).toString(), rel);
        }

    public Link linkToProductImage(Long snackShopId, Long productId, String rel) {
        return linkTo(methodOn(ProductImageController.class)
                .findImage(snackShopId, productId)).withRel(rel);
    }

    public Link linkToProductImage(Long snackShopId, Long productId) {
        return linkToProductImage(snackShopId, productId, IanaLinkRelations.SELF.value());
    }
}

