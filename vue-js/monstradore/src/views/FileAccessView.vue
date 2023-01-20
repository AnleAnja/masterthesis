<template>
  <h1>Dateizugriff</h1>
  <p>Text in Datei schreiben</p>
  <v-text-field v-model="input" placeholder="Label" />
  <div>
    <v-btn @click="writeFile">Datei speichern</v-btn>
    <!--    <v-btn @click="readFile">Datei laden</v-btn>-->
    <FileSelector
      class="fs"
      accept-extensions=".txt"
      :multiple="false"
      @changed="readFile"
    >
      <v-btn> Datei laden </v-btn>
    </FileSelector>
  </div>
  <p>{{ content }}</p>
</template>

<script>
export default {
  name: "FileAccessView",
  data() {
    return {
      content: "",
      input: "",
    };
  },
  methods: {
    async readFile(files) {
      if (files.length > 0) this.content = await files[0].text();
    },
    async writeFile() {
      const file = new Blob([this.input], { type: "text/plain" });
      const url = window.URL.createObjectURL(file);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "sample.txt");
      document.body.appendChild(link);
      link.click();
    },
  },
};
</script>

<style scoped>
@import "../../node_modules/vue-file-selector/dist/main.css";
.fs {
  justify-content: start;
  align-content: start;
  display: flex;
  flex-wrap: wrap;
}
</style>
