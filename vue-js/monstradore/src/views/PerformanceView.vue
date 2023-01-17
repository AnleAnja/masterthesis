<template>
  <h1>Performance</h1>
  <p>Die wievielte Primzahl soll berechnet werden?</p>
  <v-text-field v-model.number="input" placeholder="Zahl eingeben" />
  <div>
    <v-btn @click="calc">Berechnen</v-btn>
  </div>
  <p>Ergebnis: {{ result }}</p>
  <p>Benötigte Zeit: {{ time }} Sekunden</p>
  <v-slider />
</template>

<script>
import worker from "./worker";
const fn = worker.toString();
const fnBody = fn.substring(fn.indexOf("{") + 1, fn.lastIndexOf("}"));
const workerSourceURL = URL.createObjectURL(new Blob([fnBody]));
const bgCalc = new Worker(workerSourceURL);

export default {
  data() {
    return {
      name: "PerformanceView",
      input: "20000",
      result: 0,
      time: 0,
      start: Date.now(),
    };
  },
  methods: {
    calc() {
      this.start = Date.now();
      try {
        const number = parseInt(this.input);
        bgCalc.postMessage({
          key: "calcPrime",
          args: [number],
        });
      } catch (e) {
        console.log(e);
      }
    },
  },
  created() {
    bgCalc.onmessage = (event) => {
      if (event.data.key === "working") {
        this.$emit("loading", event.data.value);
      } else if (event.data.key === "nums") {
        let end = Date.now();
        this.time = Math.floor((end - this.start) / 1000);
        this.result = event.data.value;
      } else {
        console.error(event.data);
      }
    };
  },
};
</script>

<style scoped></style>
