import {Text, View} from 'react-native';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';

const navigators = [];

function Items({route, navigation}) {
    navigators.push(navigation);
    return (
        <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
            <Text style={{fontSize: 20, color: 'black'}}>Item {route.params.n} Content</Text>
        </View>
    );
}

const Item = createBottomTabNavigator();

function Tabs({route}) {
    return (
        <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
            <View style={{flex: 1, flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
                <Text style={{fontSize: 20, color: 'black'}}>Tab {route.params.n} Content</Text>
            </View>

            <View style={{flex: 5, flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
                <Item.Navigator>
                    <Item.Screen name="Item 1" component={Items} initialParams={{n: 1}} options={{headerShown: false}}
                                 listeners={{
                                     tabPress: (e) => {
                                         navigators.forEach((it) => it.navigate('Item 1'))
                                     }
                                 }}/>
                    <Item.Screen name="Item 2" component={Items} initialParams={{n: 2}} options={{headerShown: false}}
                                 listeners={{
                                     tabPress: (e) => {
                                         navigators.forEach((it) => it.navigate('Item 2'))
                                     }
                                 }}/>
                    <Item.Screen name="Item 3" component={Items} initialParams={{n: 3}} options={{headerShown: false}}
                                 listeners={{
                                     tabPress: (e) => {
                                         navigators.forEach((it) => it.navigate('Item 3'))
                                     }
                                 }}/>
                </Item.Navigator>
            </View>
        </View>
    );
}

export default Tabs;
