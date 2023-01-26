<template>
  <div class="row">
    <div class="col">
      <div class="row" v-for="element in data" :key="element.category">
        <div class="col">
          <div class="category row">
            {{ element.category }}
          </div>
          <div
            class="items row"
            v-for="item in element.items"
            :key="item.message"
          >
            <nav>
              <router-link :to="{ path: '/' + item.id }">{{
                item.message
              }}</router-link>
            </nav>
            <router-view />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [
        {
          category: "UI / UX",
          items: [
            { message: "Reichhaltige UI Elemente", id: 0 },
            { message: "Interaktionsdesign", id: 1 },
            { message: "Gesten", id: 2 },
            { message: "Navigation", id: 3 },
            { message: "Eingabemethoden", id: 4 },
            { message: "Multimedia", id: 5 },
            { message: "Animationen", id: 6 },
            { message: "3D Grafiken", id: 7 },
          ],
        },
        {
          category: "Gerätespezifische Funktionen",
          items: [
            { message: "Netzwerkcalls", id: 8 },
            { message: "Dateizugriff", id: 9 },
            { message: "Persistierung", id: 10 },
            { message: "Zugriff auf native Anwendungen", id: 11 },
            { message: "Kamera", id: 12 },
            { message: "GPS", id: 13 },
            { message: "Beschleunigung", id: 14 },
            { message: "Fingerabdruck / Face ID", id: 15 },
          ],
        },
        {
          category: "Algorithmen",
          items: [{ message: "Primzahlberechnung", id: 16 }],
        },
      ],
    };
  },
};
</script>

<style scoped>
.row {
  display: flex;
  flex: 1;
  flex-direction: row;
  align-content: center;
  justify-content: center;
}

.col {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-content: center;
  justify-content: center;
}

.category {
  font-weight: bold;
  font-size: large;
  text-align: start;
}

.items {
  font-weight: normal;
  font-size: medium;
}
</style>
