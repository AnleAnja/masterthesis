<template>
  <h1>Persistenz</h1>
  <p>Username eingeben</p>
  <v-text-field v-model="input" placeholder="Label" />
  <div>
    <v-btn @click="addUser">User speichern</v-btn>
  </div>
  <ul class="list">
    <li v-for="(user, i) in content" :key="i">
      {{ user }}
    </li>
  </ul>
</template>

<script>
export default {
  name: "PersistenceView",
  data() {
    return {
      content: [],
      input: "",
    };
  },
  methods: {
    async getDb() {
      return new Promise((resolve, reject) => {
        let request = window.indexedDB.open("users");

        request.onerror = (e) => {
          console.log("Error opening db", e);
          reject("Error");
        };

        request.onsuccess = (e) => {
          resolve(e.target.result);
        };

        request.onupgradeneeded = (e) => {
          console.log("onupgradeneeded");
          let db = e.target.result;
          db.createObjectStore("users", {
            autoIncrement: true,
            keyPath: "id",
          });
        };
      });
    },
    async getUsersFromDb() {
      return new Promise((resolve) => {
        let trans = this.db.transaction(["users"], "readonly");
        trans.oncomplete = () => {
          resolve(users);
        };

        let store = trans.objectStore("users");
        let users = [];

        store.openCursor().onsuccess = (e) => {
          let cursor = e.target.result;
          if (cursor) {
            users.push(cursor.value);
            cursor.continue();
          }
        };
      });
    },
    async addUser() {
      let user = {
        content: new Blob([this.input], { type: "text/plain" }),
      };
      await this.addUserToDb(user);
      await this.readUsers();
    },
    async addUserToDb(user) {
      return new Promise((resolve) => {
        let trans = this.db.transaction(["users"], "readwrite");
        trans.oncomplete = () => {
          resolve();
        };

        let store = trans.objectStore("users");
        store.add(user);
      });
    },
    async readUsers() {
      this.content = await Promise.all(
        (await this.getUsersFromDb()).map((it) => it.content.text())
      );
    },
  },
  async mounted() {
    this.db = await this.getDb();
    await this.readUsers();
  },
  // mounted() {
  //   this.getUsers();
  // },
  // data() {
  //   return {
  //     name: "PersistenceView",
  //     content: [],
  //     input: "",
  //   };
  // },
  // methods: {
  //   saveUser() {
  //     this.content.push(this.input);
  //     localStorage.setItem("users", JSON.stringify(this.content));
  //     this.input = "";
  //   },
  //   getUsers() {
  //     this.content = JSON.parse(localStorage.getItem("users"));
  //   },
  // },
};
</script>

<style scoped></style>
