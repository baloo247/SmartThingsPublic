/**
 *  Celcus TV Smartthings Integration, Currently testing on: CEL-48UHDSB-16/1
 *
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * Based on Ed Anuff and Jamie Yates Code
 *
 *  Based on Jamie Yates's example:
 *   https://gist.github.com/jamieyates79/fd49d23c1dac1add951ec8ba5f0ff8ae
 *
 *  Please make sure the TV is on when you set up the device.
 *
 *
 */

metadata {
  definition (name: "Ceclus TV Sound Mute", namespace: "BBirdy", author: "Bal Birdy") {
    capability "Switch"
}

simulator {
  status "on": "on/off: 1"
  status "off": "on/off: 0"
}

tiles(scale:2) {
  standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
    state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
    state "on", label: 'ON', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
  }

   main "switch"
   details(["switch"])
 }



   preferences {

    input name: "ipadd", type: "string", required: true, title: "TV IP Address", displayDuringSetup: true
    input name: "tv_port", type: "number", range: "0..9999", required: true, title: "TV Port Usually: 4660", displayDuringSetup: true
    input name: "smartapp_port", type: "number", range: "0..99999", required: true, title: "Smartapp Port Usually: 56789", displayDuringSetup: true
  }
 }

 def on() {
	def mute = new physicalgraph.device.HubAction(
            method: "POST",
            path: "/apps/vr/browserseturl",
            body: ("""<?xml version="1.0" ?><remote><key code="1013"/></remote>"""),
            headers: ["Content-Type":"text/plain"+" ; "+"charset=ISO-8859-1",Host:"${ipadd}:${smartapp_port}"]
    )
    log.debug mute.toString()
    sendHubCommand(mute);
}


 def off() {
	def mute = new physicalgraph.device.HubAction(
            method: "POST",
            path: "/apps/vr/browserseturl",
            body: ("""<?xml version="1.0" ?><remote><key code="1013"/></remote>"""),
            headers: ["Content-Type":"text/plain"+" ; "+"charset=ISO-8859-1",Host:"${ipadd}:${smartapp_port}"]
    )
    log.debug mute.toString()
    sendHubCommand(mute);
}