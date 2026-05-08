# 🏠 Smart Home Management System (OOP-Based Simulation)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Principles-blue?style=for-the-badge)
![Console](https://img.shields.io/badge/Interface-CLI-darkgreen?style=for-the-badge)

This project is a comprehensive Smart Home simulation developed entirely from scratch using Java. The primary motivation behind this system is to strictly apply Object-Oriented Programming (OOP) principles—such as Encapsulation, Inheritance, Abstraction, and Polymorphism—to model complex, real-world interactions in a software environment.

Rather than relying on external frameworks or libraries, this project focuses on algorithmic problem-solving and core Java capabilities. It demonstrates how a scalable software architecture is built, allowing users to seamlessly manage various household devices, monitor energy usage, and automate daily routines from a single centralized hub.

<div align="center">
  <img width="878" height="644" alt="image" src="https://github.com/user-attachments/assets/e3e7ab03-807e-403d-ac14-f1e85d58db2a" />

  <img src="image-link-1.png" alt="System Main Interface" width="700">
</div>

---

## ⚙️ Core Architecture and OOP Implementation

The backbone of this project is its highly modular class hierarchy. By utilizing OOP design patterns, the system remains clean, readable, and highly scalable.

* **Inheritance & Abstraction:** An abstract base class, `SmartDevice`, defines the fundamental properties (e.g., `deviceId`, `name`, `status`) and abstract methods (e.g., `turnOn()`, `turnOff()`). All specific devices (like `SmartLight`, `SmartThermostat`, `SmartLock`) inherit from this base class.
* **Polymorphism:** The central controller iterates through a list of generic `SmartDevice` objects, executing their overridden methods dynamically based on the specific device type.
* **Encapsulation:** Device states, temperature values, and energy consumption metrics are strictly protected using `private` access modifiers. State changes are securely handled through validation-checked `getter` and `setter` methods.

<div align="center">
  <!-- PLACE A SCREENSHOT OF THE CONSOLE OUTPUT SHOWING DEVICE LIST OR STATUS HERE -->
  <img src="image-link-2.png" alt="Device Status Output" width="700">
</div>

---

## 🚀 Detailed Features

* **Room-Based Management:** Devices can be grouped and managed according to their physical locations (e.g., Living Room, Bedroom, Garage).
* **Dynamic Status Tracking:** Real-time monitoring of operations, such as checking if the security cameras are active or reading the current room temperature.
* **Energy Consumption Simulation:** Calculates and displays the simulated power usage of active devices to promote energy-efficient management.
* **Fault Handling:** Basic exception handling to prevent invalid inputs (e.g., entering letters instead of menu numbers, or setting a thermostat to an unrealistic temperature).

<div align="center">
  <!-- PLACE A SCREENSHOT OF A SPECIFIC ACTION (E.G., ADJUSTING TEMPERATURE OR HANDLING AN ERROR) HERE -->
  <img src="image-link-3.png" alt="Control Actions and Error Handling" width="700">
</div>

---

## 🗺️ Roadmap (Future Enhancements)

This project is continuously evolving. Planned future updates include:
- [ ] Integration of a Graphical User Interface (GUI) using JavaFX.
- [ ] Connecting to a MySQL/PostgreSQL database to save device states persistently.
- [ ] Adding automated routine triggers (e.g., "Turn on lights when the door unlocks").

---

## 🛠️ Installation and Execution

To run this simulation on your local machine:

1. Clone the repository:
```bash
git clone [https://github.com/mykokko5561/smart-home-system.git](https://github.com/mykokko5561/smart-home-system.git)
