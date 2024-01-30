package com.proinsight.api.security;

import com.proinsight.domain.repository.OrderRepository;
import com.proinsight.domain.repository.SnackShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SnackShopSecurity {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    SnackShopRepository snackShopRepository;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId(){
        AuthUser authUser = (AuthUser) getAuthentication().getPrincipal();
        return authUser.getUserId();
    }

    public boolean authenticatedUserEquals(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public boolean temEscopoEscrita() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean temEscopoLeitura() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean gerenciaLanchonete(Long snackShopId) {
        if (snackShopId == null) {
            return false;
        }

        return snackShopRepository.existsAdmin(snackShopId, getUserId());
    }

    public boolean gerenciaPedidosDaLanchonete(Long orderCode) {
        return orderRepository.isOrderManagedBy(orderCode, getUserId());
    }

    public boolean podeGerenciarPedidos(Long orderCode){
        return hasAuthority("GERENCIAR_PEDIDOS") ||
                gerenciaPedidosDaLanchonete(orderCode);
    }

    public boolean podeConsultarLanchonetes() {
        return isAuthenticated();
    }

    public boolean podeGerenciarCadastroLanchonetes() {
        return hasAuthority("EDITAR_LANCHONETES");
    }

    public boolean podeConsultarUsuariosGruposPermissoes() {
        return hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podeEditarUsuariosGruposPermissoes() {
        return hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean podePesquisarPedidos(Long clienteId, Long snackShopId) {
        return hasAuthority("CONSULTAR_PEDIDOS")
                || authenticatedUserEquals(clienteId) || gerenciaLanchonete(snackShopId);
    }
    public boolean podePesquisarPedidos() {
        return isAuthenticated();
    }

    public boolean podeConsultarFormasPagamento() {
        return isAuthenticated();
    }

}

