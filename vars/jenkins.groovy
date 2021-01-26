def call() {
    properties([
            buildDiscarder(
                    logRotator(
                            artifactDaysToKeepStr: '90',
                            artifactNumToKeepStr: '20',
                            daysToKeepStr: '90',
                            numToKeepStr: '20'
                    )
            ),
            parameters([
                    string(
                            "defaultValue": "nginx:latest, alpine:latest",
                            "description": "Comma separated images. \nE.g.: nginx:latest, alpine:latest",
                            "name": "IMAGES"
                    )
            ])
    ])
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('Aqua images check') {
                    if (params.IMAGES.isEmpty())
                        error("[ERROR] IMAGES list can not be empty!")

                    images = params.IMAGES.replaceAll(" ", "").toLowerCase().split(',')
                    println("[DEBUG] images: ${images}")
                    aqua(images)
                }
            }
        }
    }
}