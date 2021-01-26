
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
                            "description": "Comma separated user's emails. \nE.g.: ivan_ivanov@epam.com, john_doe@epam.com",
                            "name": "IMAGES"
                    )
            ])
    ])
    timestamps {
        ansiColor('xterm') {
            node('master') {
                stage('Git CheckOut') {
                    if (params.IMAGES.isEmpty())
                        error("[ERROR] IMAGES list can not be empty!")

                    images = params.IMAGES.replaceAll(" ", "").toLowerCase().split(',')
                    println("[DEBUG] images: ${images}")
                    //images = ['nginx:latest']
                    aqua(images)
                }
            }
        }
    }

}