//
//  CameraView.swift
//  iosApp
//
//  Created by Anja on 26.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CameraView: View {
    @State private var image : Image? = nil
    var body: some View {
        if let image {
            image
                .resizable()
                .scaledToFit()
        } else {
            ImagePickerView(image: $image)
        }
    }
}

//struct CameraView_Previews: PreviewProvider {
//    static var previews: some View {
//        CameraView()
//    }
//}
