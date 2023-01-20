<template>
  <h1>Dateizugriff</h1>
  <p>Text in Datei schreiben</p>
  <v-text-field v-model="input" placeholder="Label" />
  <div>
    <v-btn @click="writeFile">Datei speichern</v-btn>
    <v-btn @click="readFile">Datei laden</v-btn>
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
    async readFile() {
      const pickerOpts = {
        types: [
          {
            description: "Text file",
            accept: { "text/plain": [".txt"] },
          },
        ],
        excludeAcceptAllOption: true,
        multiple: false,
      };
      let fileHandle;
      [fileHandle] = await window.showOpenFilePicker(pickerOpts);
      const fileData = await fileHandle.getFile();
      if (fileData) this.content = await fileData.text();
    },
    async writeFile() {
      const pickerOpts = {
        suggestedName: "sample.txt",
        types: [
          {
            description: "Text file",
            accept: { "text/plain": [".txt"] },
          },
        ],
        excludeAcceptAllOption: true,
        multiple: false,
      };
      const file = new Blob([this.input], { type: "text/plain" });
      const fileHandle = await window.showSaveFilePicker(pickerOpts);
      const writableStream = await fileHandle.createWritable();
      await writableStream.write(file);
      await writableStream.close();
    },
  },
};
</script>

<style scoped></style>
