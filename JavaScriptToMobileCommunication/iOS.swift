//
//  ViewController.swift
//  JSTest
//
//  Created by Edgar Žigis on 2021-11-16.
//

import UIKit
import WebKit

class ViewController: UIViewController, WKScriptMessageHandler {
    
    private var webView: WKWebView!
    private let handlerName = "iOSHandler"

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let configuration = WKWebViewConfiguration()
        configuration.userContentController.add(self, name: handlerName)
        
        webView = WKWebView(frame: self.view.frame, configuration: configuration)
        let url = URL(string: "https://url.to.web")!
        webView.load(URLRequest(url: url))
        
        view.addSubview(webView!)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0, execute: {
            self.webView.evaluateJavaScript("window.submitCardData('LTXXXXXX', 'John Doe');", completionHandler: { a, err in
                //  do something
            })
        })
    }
    
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        if message.name == handlerName {
            //  do something
        }
    }
}
