<template>
    <div class="home">
        <barra-navegacao></barra-navegacao>

        <div class="row g-0 justify-content-center">
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div v-if="!registerActive" class="card login" v-bind:class="{ error: emptyFields }">
                <fieldset>
                    <form class="col-sm-6 form-group p-2">
                        <h2>Início:</h2>
                        <p class="fs-5">Seja bem vindo!</p>
                        <div class="log-user">{{ logado.login }}</div>
                    </form>
                </fieldset>
            </div>
        </div>
    </div>

    </div>
</template>

<script>
// @ is an alias to /src

import LoginService from "@/services/LoginService";
import AuthService from "@/services/AuthService";
import BarraNavegacao from '@/components/BarraNavegacao.vue';

export default {
    name: "HomeView",
    components: {
        BarraNavegacao
    },
    methods: {
        desloga() {
            LoginService.deslogar();
            this.$router.push(`/?d=${new Date()}`);
        },
    },
    computed: {
        logado() {
            return AuthService.dados;
        },
    },
  
};

</script>

<style scoped>

.card {
  padding: 10px;
  /* Define o espaçamento interno */
}

.log-user {
    font-weight: bold; 
    font-family: Helvetica;
    text-transform: uppercase;
    font-size: 25px;
    color: blue;
}

</style>
