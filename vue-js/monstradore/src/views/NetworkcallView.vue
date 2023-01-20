<template>
  <h2>Networkcall</h2>
  <div>
    <p>{{ response }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: "NetworkcallView",
      response: "",
    };
  },
  methods: {
    async getData() {
      try {
        const response = await axios.get(
          "https://jsonplaceholder.typicode.com/posts/1"
        );
        // JSON responses are automatically parsed.
        this.response = response.data;
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {
    this.getData();
  },
};
</script>

<style scoped></style>
