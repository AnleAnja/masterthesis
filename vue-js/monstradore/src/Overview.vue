<script>
export default {
  data() {
  	return {
      data: [
      {
        category: 'UI / UX',
        items: [
          { message: 'Reichhaltige UI Elemente' }, 
          { message: 'Interaktionsdesign' }, 
          { message: 'Gesten' }, 
          { message: 'Navigation' }, 
          { message: 'Eingabemethoden' }, 
          { message: 'Multimedia' }, 
          { message: 'Animationen' }, 
          { message: '3D Grafiken' } 
        ]
      },
      {
        category: 'Gerätespezifische Funktionen',
        items: [
          { message: 'Netzwerkcalls' },
      		{ message: 'Dateizugriff' },
		      { message: 'Persistierung' },
    		  { message: 'Zugriff auf native Anwendungen' },
		      { message: 'Kamera' },
    		  { message: 'GPS' },
		      { message: 'Beschleunigung' },
    		  { message: 'Fingerabdruck / Face ID' }
        ]
      },
      {
        category: 'Algorithmen',
        items: [
          { message: 'Primzahlberechnung' }
        ]
      }
   ]}
	}
}
</script>

<template>
    <div>
	<div class="category" v-for="(element) in data">
  	    {{ element.category }}
        <div class="items" v-for="(item) in element.items">
            {{ item.message }}
        </div>
	</div>
</div>
</template>

<style scoped>

.category {
  font-weight: bold;
  font-size: large;
}

.items {
    font-size: medium;
}

</style>