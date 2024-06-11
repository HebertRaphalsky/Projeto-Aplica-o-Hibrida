<template>
  <barra-navegacao></barra-navegacao>

  <div class="cadastro">
    <div class="row g-0 justify-content-center"> <!-- Centraliza o conteúdo horizontalmente -->
      <!-- Coluna responsiva -->
      <div class="col-lg-9 col-md-6 col-sm-8"> <!-- Ajusta o tamanho da coluna em diferentes dispositivos -->
        <!-- Card de login -->
        <div v-if="!registerActive" class="card login" v-bind:class="{ error: emptyFields }">

          <h1>Projetos:</h1>

          <fieldset>
            <legend>Cadastro:</legend>
            <form class="">
              <div class="row gx-3 gy-2 align-items-center p-2">

                <!--<p><label>Login</label><input type="text" v-model="user.login" /></p>-->
                <div class="col-sm-4 form-group">
                  <input v-model="user.projeto" type="text" class="form-control" placeholder="Projeto" required>
                </div>
                <div class="col-sm-4">
                  <input v-model="user.prorfessor" type="text" class="form-control" placeholder="Professor" required>
                </div>
              </div>
              <div class="row gx-3 gy-2 col-sm-2 align-items-center p-3">
                <!--<p><label>Senha</label><input type="password" v-model="user.senha" /></p>-->
                <p><button class="btn btn-primary" @click="salvar">Salvar</button></p>
              </div>
            </form>
          </fieldset>

          <table class="table table-dark table-striped">
            <thead>
              <tr>
                <th>Id:</th>
                <th>Projeto:</th>
                <th>Professor:</th>
              </tr>
            </thead>
            <tbody class="table-group-divider">
              <tr v-for="(u, i) in projetos" v-bind:key="i" @click="abreEdit(u.id)">
                <td>{{ u.id }}</td>
                <td>{{ u.projeto }}</td>
                <td>{{ u.professor }}</td>
                <td>{{ u.permissoes.length }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BarraNavegacao from "@/components/BarraNavegacao.vue";
import AuthService from "@/services/AuthService";

export default {
  components: {
    BarraNavegacao,
  },
  data() {
    return {
      usuarios: [],
      user: {
        login: null,
        senha: null,
      },
    };
  },
  mounted() {
    console.log(AuthService.dados.token);
    this.getProjetos();
  },
  methods: {
    async getProjetos() {
      try {
        let r = await fetch("http://localhost:8080/projetos", {
          method: "GET",
          headers: { Authorization: `Bearer ${AuthService.dados.token}` },
        });
        r.json().then((j) => {
          this.projetos = j;
        });
      } catch (ex) {
        console.log("ERRO", ex);
      }
    },
    async salvar() {
      this.user.permissoes = ["ROLE_USER"];
      console.log(JSON.stringify(this.user));
      if (this.user.login != null && this.user.senha != null) {
        fetch("http://localhost:8080/projetos", {
          method: "POST",
          body: JSON.stringify(this.projeto),
          headers: {
            Authorization: `Bearer ${AuthService.dados.token}`,
            "Content-Type": "application/json",
          },
        })
          .then((r) => {
            if (r.status != 200 || r.status != 201) {
              r.json().then((j) => {
                console.log("ERRO", j);
              });
            }
            this.getProjetos();
          })
          .catch((e) => {
            console.log("ERRO", e);
          });
      }
    },
    abreEdit(id) {
      this.$router.push(`/projetos/${id}`);
    },
  },
};
</script>

<style scoped>
.card {
  padding: 20px;
  /* Define o espaçamento interno */
}

.form-control {
  
  /* Ajuste a largura conforme necessário */
  height: 30px;
  /* Ajuste a altura conforme necessário */
  font-size: 0.9em;
  /* Ajuste o tamanho da fonte conforme necessário */
  margin-left: 10px;
  /* Ajuste a margem à esquerda conforme necessário */
}


.form-group {

  /* Estilos para inputs dentro do form-group */
  input {
    margin-bottom: 20px !important;
    /* Define a margem inferior */
    border-radius: 10px !important;
    /* Define o arredondamento dos cantos */
  }
}

button {
  /* Ajuste o tamanho da fonte conforme necessário */
  box-shadow: 1px 2px 1px 1px rgba(0, 0, 0, 0.745) !important;
}

button:hover {
  color: black;
  background-color: rgb(40, 232, 6);
}

table * {
  border: solid 1px black;
}

table tbody tr {
  cursor: pointer;
}
</style>