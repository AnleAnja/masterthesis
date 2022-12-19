import React, {Component} from 'react';
import {
  StyleSheet,
  Text,
  View,
  SafeAreaView,
  SectionList,
  StatusBar,
} from 'react-native';

const DATA = [
  {
    title: 'UI / UX',
    data: [
      'Reichhaltige UI Elemente',
      'Interaktionsdesign',
      'Gesten',
      'Navigation',
      'Eingabemethoden',
      'Multimedia',
      'Animationen',
      '3D Grafiken',
    ],
  },
  {
    title: 'Gerätespezifische Funktionen',
    data: [
      'Netzwerkcalls',
      'Dateizugriff',
      'Persistierung',
      'Zugriff auf native Anwendungen',
      'Kamera',
      'GPS',
      'Beschleunigung',
      'Fingerabdruck / Face ID',
    ],
  },
  {
    title: 'Algorithmen',
    data: ['Primzahlberechnung'],
  },
];

const Item = ({title}) => (
  <View style={styles.item}>
    <Text style={styles.title}>{title}</Text>
  </View>
);

class App extends Component {
  render() {
    return (
      <SafeAreaView style={styles.container}>
        <SectionList
          sections={DATA}
          keyExtractor={(item, index) => item + index}
          renderItem={({item}) => <Item title={item} />}
          renderSectionHeader={({section: {title}}) => (
            <Text style={styles.header}>{title}</Text>
          )}
        />
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: StatusBar.currentHeight,
    marginHorizontal: 16,
  },
  item: {
    padding: 5,
  },
  header: {
    fontSize: 24,
    fontWeight: 'bold',
    padding: 5,
  },
  title: {
    fontSize: 18,
    padding: 5,
  },
});

export default App;
