# Argus

A microservice for Justin dev-ops stuffs

## Getting Started

On a raspberry pi ensure that you are running in 32 bit mode with Java 17 installed.
Ensure that you have installed p4j-1.4 

```bash
curl -sSL https://pi4j.com/install | sudo bash
sudo pi4j --wiringpi
```

Deb of :point-up copied to tools in-case that site goes away.

```bash
mkdir -p dev/bin/argus
```

install the service

```bash
sudo vim /etc/systemd/system/argus.service
# copy the contents of argus.service into it
sudo systemctl daemon-reload
sudo systemctl enable --now argus
```