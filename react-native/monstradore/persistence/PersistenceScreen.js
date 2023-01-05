import {Button, StyleSheet, Text, TextInput, View} from 'react-native';
import React, {useState} from 'react';
import {getAll, setItem, clear} from 'react-native-shared-preferences';

export default class PersistenceScreen extends React.Component {
    state = {
        text: '',
        users: [],
        amount: 0
    }

    constructor() {
        super();
        getAll((values) => {
            values.sort((left, right) => left[0].localeCompare(right[0]))
            this.setState({
                amount: values.length,
                users: values.flatMap(value => value[1])
            })
        })
    }

    render() {

        const setUsers = () => {
            setItem(`user ${this.state.amount}`, this.state.text);
            this.setState({
                users: [...this.state.users, this.state.text],
                amount: this.state.amount + 1
            })
        };

        return (
            <View>
                <Text style={styles.headline3}>Persistenz</Text>
                <Text>Username eingeben</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={newText => this.setState({text: newText})}
                    value={this.state.text}
                    placeholder="Username"
                />
                <Button title="User speichern" onPress={() => {
                    setUsers();
                }}/>
                {
                    this.state.users.map(item =>
                        <Text key={item}>{item}</Text>
                    )}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    headline3: {
        fontSize: 18,
    },
    input: {
        height: 40,
        borderWidth: 1,
        padding: 10,
    },
});
