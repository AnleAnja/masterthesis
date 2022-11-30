//
//  ImagePickerView.swift
//  iosApp
//
//  Created by Anja on 30.11.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

class ImagePickerCoordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
    
    @Binding var image: Image?
    
    init(image: Binding<Image?>) {
        _image = image
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        
        let uiImage = info[UIImagePickerController.InfoKey.originalImage] as! UIImage
        image = Image(uiImage: uiImage)
    }
    
}

struct ImagePickerView: UIViewControllerRepresentable {
    
    @Binding var image: Image?
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: UIViewControllerRepresentableContext<ImagePickerView>) {
        
    }
    
    func makeCoordinator() -> ImagePickerCoordinator {
        return ImagePickerCoordinator(image: $image)
    }
    
    func makeUIViewController(context: UIViewControllerRepresentableContext<ImagePickerView>) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.delegate = context.coordinator
        picker.sourceType = .camera
        return picker
    }
    
}
