import * as React from 'react';
import {Text, View} from 'react-native';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import Tabs from './TabsScreen'

const Tab = createMaterialTopTabNavigator();

function NavigationScreen() {
    return (
        <Tab.Navigator>
            <Tab.Screen name="Tab 1" component={Tabs} initialParams={{ n: 1 }}/>
            <Tab.Screen name="Tab 2" component={Tabs}  initialParams={{ n: 2 }}/>
            <Tab.Screen name="Tab 3" component={Tabs}  initialParams={{ n: 3 }}/>
        </Tab.Navigator>
    );
}

export default NavigationScreen;
