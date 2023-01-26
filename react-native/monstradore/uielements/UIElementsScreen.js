import {
    Button,
    FlatList,
    Image,
    ScrollView,
    Text,
    StyleSheet,
    TextInput,
    View,
} from 'react-native';
import SafeAreaView from 'react-native/Libraries/Components/SafeAreaView/SafeAreaView';
import MultiSlider from '@ptomasroos/react-native-multi-slider';
import React, {useState} from 'react';
import Switch from 'react-native/Libraries/Components/Switch/Switch';
import ContextMenu from 'react-native-context-menu-view';
import Dialog from 'react-native-dialog';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import AndroidScreen from './AndroidScreen';
import IOSScreen from './IOSScreen';
import {IconButton} from 'react-native-paper';

function GeneralUIScreen({navigation}) {
    const [isEnabled, setIsEnabled] = useState(false);
    const [isVisible, setIsVisible] = useState(false);
    const toggleSwitch = () => setIsEnabled(previousState => !previousState);
    const [text, onChangeText] = React.useState('Text Input');
    const [color, setColor] = useState('blue');
    const [circle, setCircle] = useState(false)

    return (
        <ScrollView contentContainerStyle={{padding: 10}}>
            <Text style={styles.headline1}>Reichhaltige UI Elemente</Text>
            <Text style={styles.headline2}>Grundlegende Elemente</Text>
            <Text style={styles.headline3}>Text</Text>
            <Text>
                Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam
                voluptua.
            </Text>
            <Text style={styles.headline3}>Bild</Text>
            <Image source={require('../assets/sample.jpeg')}/>
            <View style={{flex: 1}}>
                <Text style={styles.headline3}>Liste</Text>
                <ScrollView horizontal={true}>
                    <FlatList
                        data={[
                            'Item: 0',
                            'Item: 1',
                            'Item: 2',
                            'Item: 3',
                            'Item: 4',
                            'Item: 5',
                        ]}
                        renderItem={({item}) => <Text>{item}</Text>}
                    />
                </ScrollView>
            </View>
            <Text style={styles.headline3}>Button</Text>
            <Button title="Button"/>
            <Text style={styles.headline3}>Icon Button</Text>
            <IconButton
                icon="home"
                size={20}
            />
            <Text style={styles.headline2}>Elemente mit Statusverwaltung</Text>
            <Text style={styles.headline3}>Slider</Text>
            <MultiSlider/>
            <Text style={styles.headline3}>Textfeld</Text>
            <TextInput
                style={styles.input}
                onChangeText={onChangeText}
                value={text}
            />
            <Text style={styles.headline3}>Switch / Toggle</Text>
            <Switch onValueChange={toggleSwitch} value={isEnabled}/>
            <Text style={styles.headline2}>Plattformspezifische Elemente</Text>
            <View style={styles.row}>
                <Button title="Android" onPress={() => navigation.navigate('AndroidScreen')}/>
                <Button title="iOS" onPress={() => navigation.navigate('IOSScreen')}/>
            </View>
            <Text style={styles.headline2}>Fortgeschrittene Elemente</Text>
            <View style={styles.row}>
                <SafeAreaView>
                    <ContextMenu
                        title={"Menu"}
                        actions={[{title: "Menu"}]}
                        dropdownMenuMode={true}
                    >
                        <View>
                            <Button title="Menu" onPress={() => {
                            }}/>
                        </View>
                    </ContextMenu>
                </SafeAreaView>
                <Button title="Dialog" onPress={() => setIsVisible(true)}/>
            </View>
            <View style={styles.center}>
                <Dialog.Container visible={isVisible}>
                    <Dialog.Title>Dialog</Dialog.Title>
                    <Dialog.Description>Dialog Text</Dialog.Description>
                    <Dialog.Button label="OK" onPress={() => setIsVisible(false)}/>
                    <Dialog.Button label="Cancel" onPress={() => setIsVisible(false)}/>
                </Dialog.Container>
            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
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
    input: {
        height: 40,
        borderWidth: 1,
        padding: 10,
    },
    row: {
        flex: 1,
        flexDirection: 'row',
    },
    center: {
        alignContent: 'center',
        justifyContent: 'center',
    },
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center'
    },
    rectangle: {
        width: 200,
        height: 200,
    },
    spacer: {
        height: 16,
    }
});

const UIStack = createNativeStackNavigator();

function UIElementsScreen() {
    return (
        <UIStack.Navigator initialRouteName="GeneralUI">
            <UIStack.Screen name="GeneralUI" component={GeneralUIScreen} options={{headerShown: false}}/>
            <UIStack.Screen name="AndroidScreen" component={AndroidScreen} options={{headerShown: false}}/>
            <UIStack.Screen name="IOSScreen" component={IOSScreen} options={{headerShown: false}}/>
        </UIStack.Navigator>
    )
}

export default UIElementsScreen;
