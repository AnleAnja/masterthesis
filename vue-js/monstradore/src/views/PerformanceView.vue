<template>
  <h1>Performance</h1>
  <p>Die wievielte Primzahl soll berechnet werden?</p>
  <v-text-field v-model.number="input" placeholder="Zahl eingeben" />
  <div>
    <v-btn @click="calcPrime">Berechnen</v-btn>
  </div>
  <p>Ergebnis: {{ result }}</p>
  <p>Benötigte Zeit: {{ time }} Sekunden</p>
  <v-slider />
</template>

<script>
export default {
  data() {
    return {
      name: "PerformanceView",
      input: 20000,
      result: 0,
      time: 0,
    };
  },
  methods: {
    calcPrime() {
      let num = 1;
      let count = 0;
      while (count < this.input) {
        num++;
        let i = 2;
        while (i <= num) {
          if (num % i === 0) {
            break;
          }
          i++;
        }
        if (i === num) {
          count++;
        }
      }
      this.result = num;
    },
  },
};
</script>

<style scoped></style>
