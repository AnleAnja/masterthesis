import 'react-native-gesture-handler';
import {AppBar} from '@react-native-material/core';
import {Button, ScrollView, StyleSheet, Text, View} from 'react-native';
import * as React from 'react';
import {useState} from 'react';
import {
  Card,
  Chip,
  Paragraph,
  RadioButton,
  Snackbar,
  Title,
} from 'react-native-paper';
import {FAB} from 'react-native-elements';
import SegmentedControlTab from 'react-native-segmented-control-tab';
import CheckBox from 'expo-checkbox';
import {DateTimePickerAndroid} from '@react-native-community/datetimepicker';
import {createDrawerNavigator} from '@react-navigation/drawer';
import IOSScreen from './IOSScreen';

// function Feed() {
//   return (
//     <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
//       <Text>Feed Screen</Text>
//     </View>
//   );
// }

function Content({ navigation }) {
    console.log("hallo2");
    const [visible, setVisible] = React.useState(false);
    const onToggleSnackBar = () => setVisible(!visible);
    const onDismissSnackBar = () => setVisible(false);
    const [selectedIndex, setSelectedIndex] = useState(0);
    const handleTabSelect = index => {
        setSelectedIndex(index);
    };
    const [checked, setChecked] = React.useState(false);
    const [toggleCheckBox, setToggleCheckBox] = useState(false);
    const [date, setDate] = useState(new Date(1598051730000));

    const onChange = (event, selectedDate) => {
        const currentDate = selectedDate;
        setDate(currentDate);
    };

    const showMode = currentMode => {
        DateTimePickerAndroid.open({
            value: date,
            onChange,
            mode: currentMode,
            is24Hour: true,
        });
    };

    const showDatepicker = () => {
        showMode('date');
    };

    const showTimepicker = () => {
        showMode('time');
    };

    return (
      <ScrollView style={styles.parent}>
        <Button
            onPress={() => {
              navigation.openDrawer();
              console.log('hallo');
            }}
            title="Go to notifications"
        />
        <Text style={styles.headline2}>Struktur und Navigation</Text>
        <Text style={styles.headline3}>App Bar Top</Text>
        <Text style={styles.exists}>Verfügbar, s.o.</Text>
        <Text style={styles.headline3}>App Bar Bottom</Text>
        <Text style={styles.unavailable}>
          Nur mit Navigation verfügbar, siehe Feature "Navigation"
        </Text>
        <Text style={styles.headline3}>Navigation Drawer</Text>
        <Text style={styles.available}>Verfügbar, swipe left to right</Text>
        <Text style={styles.headline3}>Navigation Rail</Text>
        <Text style={styles.unavailable}>Nicht verfügbar</Text>
        <Text style={styles.headline2}>Grundlegende Android Elemente</Text>
        <Text style={styles.headline3}>Card</Text>
        <Card>
          <Card.Content>
            <Title>Card Title</Title>
            <Paragraph>Card Content</Paragraph>
          </Card.Content>
        </Card>
        <Text style={styles.headline3}>Chip</Text>
        <Chip>Chip Content</Chip>
        <Text style={styles.headline3}>Snackbar</Text>
        <Button onPress={onToggleSnackBar} title="Snackbar" />
        <Snackbar visible={visible} onDismiss={onDismissSnackBar}>
          Snackbar Content
        </Snackbar>
        <Text style={styles.headline3}>Segmented Buttons</Text>
        <SegmentedControlTab
          values={['Item 1', 'Item 2', 'Item 3']}
          selectedIndex={selectedIndex}
          onTabPress={handleTabSelect}
        />
        <Text style={styles.headline3}>Floating Action Buttons</Text>
        <FAB title="FAB" />
        <Text style={styles.headline2}>Interaktive Android Elemente</Text>
        <Text style={styles.headline3}>Checkbox</Text>
        <CheckBox
          disabled={false}
          value={toggleCheckBox}
          onValueChange={newValue => setToggleCheckBox(newValue)}
        />
        <Text style={styles.headline3}>Radio Buttons</Text>
        <View style={styles.row}>
          <RadioButton
            value="first"
            status={checked === 'first' ? 'checked' : 'unchecked'}
            onPress={() => setChecked('first')}
          />
          <RadioButton
            value="second"
            status={checked === 'second' ? 'checked' : 'unchecked'}
            onPress={() => setChecked('second')}
          />
        </View>
        <Text style={styles.headline3}>Time Picker</Text>
        <Button onPress={showDatepicker} title="Date Picker" />
        <Text style={styles.headline3}>Date Picker</Text>
        <Button onPress={showTimepicker} title="Time Picker" />
      </ScrollView>
    );
}

function AndroidScreen() {
    const Drawer = createDrawerNavigator();

    return (
        <Drawer.Navigator useLegacyImplementation initialRouteName="Content">
            <Drawer.Screen name="TopAppBar" component={Content} />
        </Drawer.Navigator>
    );
}

function NavDrawer() {
  return (
    <View>
      <Text style={styles.headline1}>Navigation Drawer Content</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  parent: {
    padding: 10,
  },
  headline1: {
    fontSize: 22,
    fontWeight: 'bold',
  },
  headline2: {
    fontSize: 20,
    fontWeight: '200',
  },
  headline3: {
    fontSize: 18,
  },
  exists: {
    color: 'blue',
  },
  unavailable: {
    color: 'red',
  },
  bottom: {
    backgroundColor: 'blue',
    position: 'absolute',
    alignSelf: 'flex-end',
    left: 0,
    right: 0,
    bottom: 0,
    height: 60,
  },
  row: {
    flex: 1,
    flexDirection: 'row',
  },
});

export default AndroidScreen;
