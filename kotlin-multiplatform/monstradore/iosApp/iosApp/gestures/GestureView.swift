//
//  GestureView.swift
//  iosApp
//
//  Created by Anja on 22.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct GestureView: View {
    @State private var uiimage = UIImage(named: "sample.jpeg")
    
    @GestureState private var scaleState: CGFloat = 1
    @GestureState private var offsetState = CGSize.zero
    
    @State private var offset = CGSize.zero
    @State private var scale: CGFloat = 1
    
    var magnification: some Gesture {
        MagnificationGesture()
            .updating($scaleState) { currentState, gestureState, _ in
                gestureState = currentState
            }
            .onEnded { value in
                scale *= value
            }
    }
    
    var body: some View {
        Image(uiImage: uiimage!)
            .resizable()
            .scaledToFit()
            .scaleEffect(self.scale * scaleState)
            .offset(x: offset.width + offsetState.width, y: offset.height + offsetState.height)
            .gesture(magnification)
    }
}

//struct GestureView_Previews: PreviewProvider {
//    static var previews: some View {
//        GestureView()
//    }
//}
