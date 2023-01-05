import React from 'react';
import {StyleSheet, Text, View, SafeAreaView, SectionList} from 'react-native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {NavigationContainer} from '@react-navigation/native';
import EmptyScreen from './EmptyScreen';
import InputMethodsScreen from './inputmethods/InputMethodsScreen';
import GesturesScreen from './gestures/GesturesScreen';
import NavigationScreen from './navigation/NavigationScreen';
import MultimediaScreen from './multimedia/MultimediaScreen';
import AnimationsScreen from './animations/AnimationsScreen';
import ObjectsScreen from './objects/ObjectsScreen';
import NetworkCallScreen from './networkcall/NetworkCallScreen';
import FileAccessScreen from './fileaccess/FileAccessScreen';
import PersistenceScreen from './persistence/PersistenceScreen';

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

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginHorizontal: 16,
  },
  item: {
    padding: 5,
  },
  header: {
    fontSize: 22,
    fontWeight: 'bold',
    padding: 5,
  },
  title: {
    fontSize: 18,
    padding: 5,
  },
});

function OverviewScreen({navigation}) {
  return (
    <SafeAreaView style={styles.container}>
      <SectionList
        sections={DATA}
        keyExtractor={(item, index) => item + index}
        renderItem={({item}) => (
          <View style={styles.item}>
            <Text
              onPress={() => {
                /*if (item === 'Reichhaltige UI Elemente') {
                  navigation.navigate();
                } else if (item === 'Interaktionsdesign') {
                  navigation.navigate();
                } else*/ if (item === 'Gesten') {
                  navigation.navigate('Gestures');
                } else if (item === 'Navigation') {
                  navigation.navigate('Navigation');
                } else if (item === 'Eingabemethoden') {
                  navigation.navigate('InputMethods');
                } else if (item === 'Multimedia') {
                  navigation.navigate('Multimedia');
                } else if (item === 'Animationen') {
                  navigation.navigate('Animations');
                } else if (item === '3D Grafiken') {
                  navigation.navigate('Objects');
                } else if (item === 'Netzwerkcalls') {
                  navigation.navigate('NetworkCall');
                } else if (item === 'Dateizugriff') {
                  navigation.navigate('FileAccess');
                } else if (item === 'Persistierung') {
                  navigation.navigate('Persistence');
                /*} else if (item === 'Zugriff auf native Anwendungen') {
                  navigation.navigate();
                } else if (item === 'Kamera') {
                  navigation.navigate();
                } else if (item === 'GPS') {
                  navigation.navigate();
                } else if (item === 'Beschleunigung') {
                  navigation.navigate();
                } else if (item === 'Fingerabdruck / Face ID') {
                  navigation.navigate();
                } else if (item === 'Primzahlberechnung') {
                  navigation.navigate();*/
                } else {
                  navigation.navigate('Empty');
                }
              }
              }
              style={styles.title}>
              {item}
            </Text>
          </View>
        )}
        renderSectionHeader={({section: {title}}) => (
          <Text style={styles.header}>{title}</Text>
        )}
      />
    </SafeAreaView>
  );
}

const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Overview" component={OverviewScreen} />
        <Stack.Screen name="Gestures" component={GesturesScreen} />
        <Stack.Screen name="Navigation" component={NavigationScreen} />
        <Stack.Screen name="InputMethods" component={InputMethodsScreen} />
        <Stack.Screen name="Multimedia" component={MultimediaScreen} />
        <Stack.Screen name="Animations" component={AnimationsScreen} />
        <Stack.Screen name="Objects" component={ObjectsScreen} />
        <Stack.Screen name="NetworkCall" component={NetworkCallScreen} />
        <Stack.Screen name="FileAccess" component={FileAccessScreen} />
        <Stack.Screen name="Persistence" component={PersistenceScreen} />
        <Stack.Screen name="Empty" component={EmptyScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
