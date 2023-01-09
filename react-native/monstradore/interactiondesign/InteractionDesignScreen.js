import {View, Text, Platform} from 'react-native';
import {ListItem} from 'react-native-elements';
import SafeAreaView from 'react-native/Libraries/Components/SafeAreaView/SafeAreaView';
import ContextMenu from 'react-native-context-menu-view';
import {useState} from 'react';

function InteractionDesignScreen() {
    const [list, setList] = useState(Array.from({length: 25}, (v, i) => `Dieses Element hat den Index ${i}.`));
    const onLongPressDelete = (index) => {
    }

    return (
        <View>
            {
                list.map((element, index) =>
                    (
                        <ListItem
                            title={element}
                            bottomDivider
                            key={index}
                        >
                            {
                                Platform.OS === 'android'
                                    ? <SafeAreaView>
                                        <ContextMenu
                                            title={"Löschen"}
                                            actions={[
                                                {title: "Löschen"}
                                            ]}
                                            onPress={(e) => {
                                                setList(list.filter((_e, i) => i !== index));
                                            }
                                            }
                                        >
                                            <Text>{element}</Text>
                                        </ContextMenu>
                                    </SafeAreaView>
                                    : <Text>ios</Text>
                            }

                        </ListItem>

                    )
                )
            }
        </View>
    )
}

export default InteractionDesignScreen;
