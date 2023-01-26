import {Animated, Platform, Text, View, StyleSheet} from 'react-native';
import {ListItem} from 'react-native-elements';
import SafeAreaView from 'react-native/Libraries/Components/SafeAreaView/SafeAreaView';
import ContextMenu from 'react-native-context-menu-view';
import {useState} from 'react';
import {Swipeable, TouchableOpacity} from 'react-native-gesture-handler';

function InteractionDesignScreen() {
    const [list, setList] = useState(Array.from({length: 25}, (v, i) => `Dieses Element hat den Index ${i}.`));
    const [row, setRow] = useState(new Map());

    const renderRightActions = (index) => (
        progress: Animated.AnimatedInterpolation,
        dragX: Animated.AnimatedInterpolation,
    ) => {
        const opacity = dragX.interpolate({
            inputRange: [-150, 0],
            outputRange: [1, 0],
            extrapolate: 'clamp',
        });

        return (
            <View style={styles.swipedRow}>
                <View style={styles.swipedConfirmationContainer}>
                    <Text style={styles.deleteConfirmationText}>Are you sure?</Text>
                </View>
                <Animated.View style={[styles.deleteButton, {opacity}]}>
                    <TouchableOpacity
                        onPress={(e) => {
                            setList(list.filter((_e, i) => i !== index));
                            row.get(index).close()
                        }}
                    >
                        <Text style={styles.deleteButtonText}>Delete</Text>
                    </TouchableOpacity>
                </Animated.View>
            </View>
        );
    };

    const androidView = (element, index) => {
        return (
            <ListItem
                title={element}
                bottomDivider
                key={index}>
                <SafeAreaView>
                    <ContextMenu
                        title={"Löschen"}
                        actions={[
                            {title: "Löschen"}
                        ]}
                        onPress={(e) => {
                            setList(list.filter((_e, i) => i !== index));
                        }}>
                        <Text>{element}</Text>
                    </ContextMenu>
                </SafeAreaView>
            </ListItem>
        )
    }

    const iOSView = (element, index) => {
        return (
            <Swipeable
                renderRightActions={renderRightActions(index)}
                key={index}
                ref={(ref) => setRow((map) => map.set(index, ref))}
            >
                <ListItem
                    title={element}
                    bottomDivider
                >
                    <Text>{element}</Text>
                </ListItem>
            </Swipeable>
        )
    }

    return (
        <View>
            {
                list.map((element, index) => Platform.OS === 'android' ? androidView(element, index) : iOSView(element, index))
            }
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        minHeight: 300,
    },
    row: {
        flexDirection: 'row',
        flex: 1,
        alignItems: 'center',
        paddingLeft: 5,
        backgroundColor: '#efefef',
        margin: 20,
        minHeight: 50,
    },
    swipedRow: {
        flexDirection: 'row',
        flex: 1,
        alignItems: 'center',
        paddingLeft: 5,
        backgroundColor: '#818181',
        minHeight: 50,
    },
    swipedConfirmationContainer: {
        flex: 1,
    },
    deleteConfirmationText: {
        color: '#fcfcfc',
        fontWeight: 'bold',
    },
    deleteButton: {
        backgroundColor: '#b60000',
        flexDirection: 'column',
        justifyContent: 'center',
        height: '100%',
    },
    deleteButtonText: {
        color: '#fcfcfc',
        fontWeight: 'bold',
        padding: 3,
    },
});

export default InteractionDesignScreen;
