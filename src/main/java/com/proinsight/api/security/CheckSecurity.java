package com.proinsight.api.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {
    

    public @interface SnackShops {

        @PreAuthorize("@snackShopSecurity.podeGerenciarCadastroLanchonetes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarCadastro { }

        @PreAuthorize("@snackShopSecurity.podeGerenciarFuncionamentoLanchonetes(#snackShopId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarFuncionamento { }

        @PreAuthorize("@snackShopSecurity.podeConsultarLanchonetes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface Orders {

        @PreAuthorize("@snackShopSecurity.isAuthenticated()")
        @PostAuthorize("@snackShopSecurity.hasAuthority('CONSULTAR_PEDIDOS') || @snackShopSecurity.authenticatedUserEquals(returnObject) || @snackShopSecurity.gerenciaLanchonete(returnObject)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar { }

        @PreAuthorize("@snackShopSecurity.podePesquisarPedidos(#filter.clientId, #filter.snackShopId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodePesquisar { }

        @PreAuthorize("@snackShopSecurity.isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCriar { }

        @PreAuthorize("@snackShopSecurity.podeGerenciarPedidos(#orderCode)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarPedidos { }

    }

    public @interface PaymentMethods {

        @PreAuthorize("@snackShopSecurity.hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }

        @PreAuthorize("@snackShopSecurity.podeConsultarFormasPagamento()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface UserGroupsPermissions {

        @PreAuthorize("@snackShopSecurity.hasAuthority('SCOPE_WRITE') and "
                + "@algaSecurity.authenticatedUserEquals(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha { }

        @PreAuthorize("@snackShopSecurity.hasAuthority('SCOPE_WRITE') and (@snackShopSecurity.hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
                + "@snackShopSecurity.authenticatedUserEquals(#usuarioId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario { }

        @PreAuthorize("@snackShopSecurity.podeEditarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }


        @PreAuthorize("@snackShopSecurity.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }
    
} 

