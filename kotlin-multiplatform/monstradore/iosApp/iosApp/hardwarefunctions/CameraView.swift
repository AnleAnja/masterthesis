//
//  CameraView.swift
//  iosApp
//
//  Created by Anja on 26.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CameraView: View {
    @StateObject private var model = FrameHandler()
    var body: some View {
        FrameView(image: model.frame)
            .ignoresSafeArea()
    }
}

//struct CameraView_Previews: PreviewProvider {
//    static var previews: some View {
//        CameraView()
//    }
//}
