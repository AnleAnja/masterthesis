//
//  AnimationsView.swift
//  iosApp
//
//  Created by Anja on 24.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct AnimationsView: View {
    @State var animateTransition = false
    @State var animateHide = false
    @State var animateCrossfade = false

    private var small = 64.0
    private var large = 128.0
    
    var body: some View {
        VStack {
            Text("Animationen")
                .font(.headline)
                .multilineTextAlignment(.leading)
            Button(action: {
                self.animateTransition.toggle()
            }) {
                Text("Transition")
            }
            Rectangle()
                .foregroundColor(.gray)
                .frame(
                    width: animateTransition ? large : small,
                    height: animateTransition ? large : small
                )
                .animation(.default)
            Button(action: {
                withAnimation {
                    self.animateHide.toggle()
                }
            }) {
                if(animateHide) {
                    Text("Hide")
                }
                else {
                    Text("Show")
                }
            }
            if(animateHide) {
                Rectangle()
                    .foregroundColor(.gray)
                    .frame(
                        width: large,
                        height: large
                    )
                    .animation(.default)
            }
        }
        .frame(alignment: .topLeading)
        .navigationBarTitleDisplayMode(.inline)
    }
}

//struct AnimationsView_Previews: PreviewProvider {
//    static var previews: some View {
//        AnimationsView()
//    }
//}
