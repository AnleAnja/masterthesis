//
//  ObjectView.swift
//  iosApp
//
//  Created by Anja on 29.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SceneKit

struct ObjectView: View {
    var body: some View {
        VStack {
            Text("3D Objekte")
                .font(.headline)
            SceneView(scene: SCNScene(named: "sampleobject.obj"), options: [.autoenablesDefaultLighting, .allowsCameraControl])
                .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height * 0.8)
        }
    }
}

//struct ObjectView_Previews: PreviewProvider {
//    static var previews: some View {
//        ObjectView()
//    }
//}
