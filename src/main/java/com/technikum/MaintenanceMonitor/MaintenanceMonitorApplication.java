package com.technikum.MaintenanceMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MaintenanceMonitorApplication {
	String message = "-";
	public static void main(String[] args) {
		SpringApplication.run(MaintenanceMonitorApplication.class, args);
	}

	@GetMapping("/")
	public String monitor(){
		//taken from https://moodle.technikum-wien.at/mod/resource/view.php?id=1027819
		return "\n" +
				"<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"\n" +
				"<head>\n" +
				"    <title>Maintenance Monitor</title>\n" +
				"    <!-- Required meta tags -->\n" +
				"    <meta charset=\"utf-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
				"\n" +
				"    <!-- Bootstrap CSS -->\n" +
				"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\n" +
				"        integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
				"\n" +
				"    <style>\n" +
				"        html { height:100% }\n" +
				"        body { height:100% }\n" +
				"        .container-fluid {height:100%}\n" +
				"        .container-fluid > * {width: 100%;}\n" +
				"        #updated { font-size: xx-small; }\n" +
				"    </style>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"    <div id=\"container\" class=\"container-fluid d-flex\">\n" +
				"        <div class=\"d-flex align-items-center justify-content-center\">\n" +
				"            <div id=\"mainContent\">\n" +
				"                <h1> Maintenance Monitor </h1>\n" +
				"                <p id=\"message\" class=\"text-center\">no information</p>\n" +
				"                <p id=\"updated\" class=\"text-center\"></p>\n" +
				"            </div>\n" +
				"        </div>\n" +
				"    </div>\n" +
				"\n" +
				"    <!-- Optional JavaScript -->\n" +
				"    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\n" +
				"    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\n" +
				"        integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\n" +
				"        crossorigin=\"anonymous\"></script>\n" +
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\n" +
				"        integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\n" +
				"        crossorigin=\"anonymous\"></script>\n" +
				"    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\n" +
				"        integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\n" +
				"        crossorigin=\"anonymous\"></script>\n" +
				"\n" +
				"    <script>\n" +
				"        var containerControl = document.getElementById('container');\n" +
				"        var messageControl = document.getElementById('message');\n" +
				"        var updateControl = document.getElementById('updated');\n" +
				"\n" +
				"        function checkState() {\n" +
				"            // https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Client-side_web_APIs/Fetching_data\n" +
				"            // https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch\n" +
				"            fetch('http://localhost:8080/api/maintenanceMode').then(response => {\n" +
				"                response.text().then(text => {\n" +
				"                    \n" +
				"                    // https://cssgradient.io/\n" +
				"                    if(text == '-') {\n" +
				"                        messageControl.innerText = \"\";\n" +
				"                        containerControl.style.background = 'linear-gradient(90deg, rgba(255,255,255,1) 0%, rgba(  9,245, 68, 0.5) 50%, rgba(255,255,255,1) 100%)';\n" +
				"                    } else {\n" +
				"                        messageControl.innerText = text;\n" +
				"                        containerControl.style.background = 'linear-gradient(90deg, rgba(255,255,255,1) 0%, rgba(245,  9,  9, 0.5) 50%, rgba(255,255,255,1) 100%)';\n" +
				"                    }\n" +
				"\n" +
				"                });\n" +
				"                updateControl.innerText = 'last update: ' + (new Date()).toLocaleTimeString()\n" +
				"            }).catch((reason)=> {\n" +
				"                messageControl.innerText = \"error occured\";\n" +
				"                containerControl.style.background = 'linear-gradient(90deg, rgba(255,255,255,1) 0%, rgba(245,  9,  9, 0.5) 50%, rgba(255,255,255,1) 100%)';\n" +
				"                updateControl.innerText = 'last update: ' + (new Date()).toLocaleTimeString()\n" +
				"            });\n" +
				"        };\n" +
				"\n" +
				"        checkState();\n" +
				"        setInterval(checkState, 5000);\n" +
				"    </script>\n" +
				"</body>\n" +
				"\n" +
				"</html>\n";
	}

	@GetMapping("/api/maintenanceMode")
	public String maintenanceMode(){
		return message;
	}

	@GetMapping("/api/maintenanceMode/reset")
	public int reset(){
		message = "-";
		return 1;
	}

	@GetMapping("/api/maintenanceMode/set")
	public int set(@RequestParam(name = "message") String message){
		this.message = message;
		return 1;
	}


}
