import {
    Button,
    UIManager,
    View,
    StyleSheet,
    LayoutAnimation,
    Animated,
} from 'react-native';
import React, { useState } from 'react';

UIManager.setLayoutAnimationEnabledExperimental &&
UIManager.setLayoutAnimationEnabledExperimental(true);

export default class AnimationsScreen extends React.Component {
    state = {
        width: 128,
        height: 128,
        small: 64,
        large: 128,
        isVisible: true,
        opacity: new Animated.Value(1),
    };

    render() {
        const animatedStyles = {
            opacity: this.state.opacity,
        };
        return (
            <View style={styles.container}>
                <Button
                    title="Transition"
                    onPress={() => {
                        LayoutAnimation.spring();
                        this.setState({
                            width: this.state.width === this.state.large ? this.state.small : this.state.large,
                            height: this.state.height === this.state.large ? this.state.small : this.state.large,
                        });
                    }}
                />
                <View
                    style={[
                        styles.box,
                        {width: this.state.width, height: this.state.height},
                    ]}
                />
                <Button
                    style={{padding: 10}}
                    title={this.state.isVisible ? 'Hide' : 'Show'}
                    onPress={() => {
                        this.setState({isVisible: !this.state.isVisible});
                        if (this.state.isVisible) {
                            new Animated.timing(this.state.opacity, {
                                toValue: 0,
                                duration: 300,
                                useNativeDriver: true,
                            }).start();
                        } else {
                            new Animated.timing(this.state.opacity, {
                                toValue: 1,
                                duration: 300,
                                useNativeDriver: true,
                            }).start();
                        }
                    }}
                />
                <Animated.View style={[styles.box, animatedStyles]} />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'space-evenly',
    },
    box: {
        width: 128,
        height: 128,
        backgroundColor: 'grey',
    },
});
