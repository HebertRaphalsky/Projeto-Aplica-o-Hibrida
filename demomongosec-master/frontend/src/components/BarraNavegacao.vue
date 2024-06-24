<template>
  <div class="container-fluid mb-3 p-3 bg">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a href="/" class="navbar-brand">
          <img src="@/assets/logo.png" alt="Logo" />
        </a>

        <!-- Botão mobile -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <router-link to="/" class="nav-link">Início</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/projetos" class="nav-link"
                >Projetos</router-link
              >
            </li>
            <li class="nav-item">
              <router-link to="/usuarios" class="nav-link"
                >Usuários</router-link
              >
            </li>
            <li class="nav-item">
              <router-link to="/sobre" class="nav-link">Sobre</router-link>
            </li>
          </ul>

          <div class="deslogar">
            <div class="log-user">{{ logado.login }}</div>
            | <button class="bot-deslogar" @click="desloga">Sair</button>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import LoginService from "@/services/LoginService";
import AuthService from "@/services/AuthService";

export default {
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
button {
  margin: 0 10px;
  box-shadow: 1px 2px 1px 1px rgba(0, 0, 0, 0.745) !important;
}

.navbar {
  border-radius: 10px;
  font-size: 18px;
}

.navbar-brand img{
  width: 110px;
  height: 30px;
  margin-right: 50px;
  margin-left: 5px;
}

.nav-item {
  margin-right: 10px;
}

.deslogar {
  margin-left: auto;
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.bot-deslogar {
  border-radius: 6px !important;
}

.bot-deslogar:hover {
  border-radius: 6px !important;
  background-color: rgb(254, 229, 2) !important;
}

.log-user {
  font-weight: bold;
  font-family: Helvetica;
  text-transform: uppercase;
  font-size: 19px;
  color: rgb(17, 0, 255);
}

/* Estilos responsivos */
@media (max-width: 768px) {
  .navbar-brand {
    margin-right: 20px;
    margin-left: 10px;
  }

  .navbar-nav {
    flex-direction: column;
  }
}
</style>