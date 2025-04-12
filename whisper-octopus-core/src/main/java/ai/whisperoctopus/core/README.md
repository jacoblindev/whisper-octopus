# Whisper-Octopus Core Module

The core module of the Whisper-Octopus project contains the essential components and services that form the backbone of the application. This module is responsible for handling the core functionalities, including data models, repositories, services, exceptions, configurations, utilities, and constants.

## Package Structure Overview

```text
ai.whisperoctopus.core/
├── model/                  # Contains all data models used throughout the application.
│   ├── entity/             # Domain entities representing the core business objects that map directly to database tables.
│   │   ├── base/           # Base abstract classes for entity definitions.
│   │   ├── tenant/         # Entities that belong to specific tenants and require tenant isolation.
│   │   └── system/         # System-level entities that are not tenant-specific.
│   ├── dto/                # Data Transfer Objects used for transferring data between layers.
│   ├── enumeration/        # Enumerations defining various constants and types used in the application.
│   └── event/              # Event classes for handling domain events and notifications.
├── repository/             # Contains all repository interfaces for data access.
│   ├── base/               # Base repository interfaces for common CRUD operations.
│   └── tenant/             # Repositories specific to tenant data access.
├── service/                # Contains all service interfaces and implementations.
│   ├── base/               # Base service interfaces for common business logic.
│   ├── tenant/             # Services specific to tenant operations and business logic.
│   ├── system/             # Services related to system-level operations.
│   └── impl/               # Implementations of the service interfaces.
├── exception/              # Custom exception classes for handling application-specific errors.
│   ├── tenant/             # Tenant-related exceptions.
│   ├── security/           # Security-related exceptions.
│   ├── resource/           # Resource-related exceptions.
│   └── validation/         # Validation-related exceptions.
├── config/                 # Configuration classes for setting up the application context.
├── util/                   # Utility classes providing helper methods and common functionalities.
│   ├── security/           # Security-related utility classes.
│   ├── validation/         # Validation-related utility classes.
│   ├── mapper/             # Utility classes for mapping between different object types.
│   └── tenant/             # Tenant-related utility classes.
└── constant/               # Constants and static values used throughout the application.
    ├── tenant/             # Tenant-related constants.
    ├── security/           # Security-related constants.
    └── resource/           # Resource-related constants.
```
