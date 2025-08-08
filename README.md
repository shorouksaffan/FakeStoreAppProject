#  FakeStore E-Commerce App
* Android shopping app using the Fake Store API

## Features
- Browse products with **pagination**
- View product details
- Add items to **local cart** (Room Database)
- Browse by categories

---
## Branch Strategy

- `master` – Final stable code
- `dev` – Shared development branch
- `feature/*` – Each team member’s task
  
---
# Architecture Implementation Guide

## 1. Data Layer (`/data`)
**Purpose**: Handles all data operations (local + remote)

### Local Persistence (`/local`)
- `AppDatabase.kt` - Room database setup
- `CartDao.kt` - Database operations for cart
- `CartItem.kt` - Entity model for cart items

### Network (`/network`)
- `Api.kt` - Retrofit interface definitions
- `RetrofitInstance.kt` - Retrofit client configuration

### Shared Models (`/model`)
- `Product.kt` - Product data structure
- `Category.kt` - Category data structure

### Paging (`/paging`)
- `ProductPagingSource.kt` - Pagination logic

### Repository (`/repository`)
- `ProductRepository.kt` - Interface
- `ProductRepositoryImpl.kt` - Concrete implementation

---

## 2. UI Layer (`/ui`)
**Purpose**: All visual components and navigation

### Components (`/components`)
- `ProductCard.kt` - Reusable product display
- `CategoryCard.kt` - Category display component
- `AppSnackbar.kt` - Standardized notifications

### Navigation (`/navigation`)
- `NavGraph.kt` - All navigation routes

### Screens (`/screens`)
- `ProductListScreen.kt` - Main product grid
- `ProductDetailScreen.kt` - Single product view
- `CategoryScreen.kt` - Category listing
- `CategoryProductScreen.kt` - Products by category
- `CartScreen.kt` - Cart management

### Themes (`/themes`)
- `Color.kt` - Color palette
- `Type.kt` - Typography
- `Theme.kt` - MaterialTheme configuration


### ViewModels (`/viewmodels`)
**Purpose**: Manage screen-specific state and logic
- `ProductListViewModel.kt` - Handles product listing
- `ProductDetailViewModel.kt` - Manages product details
- `CategoryViewModel.kt` - Category operations
- `CartViewModel.kt` - Cart state management

### Utilities (`/utils`)
- `NetworkMonitor.kt` - Internet connection observer
- `Resource.kt` - Standard API response wrapper

---
## Project Coordination

- Repository setup, README, branch strategy (`dev` / `feature/*` / `master`), and task distribution were handled by **Shorouk Saffan**.

---

# Team Roles

- task1:`local + model -> eslam magdy`          
- task2:`Network + Paging + Repository -> eslam magdy`            
- task3:` UI Components + Themes -> ismail ayman`   
- task4: `UI Navigation + Screens -> Ebrahim Gamal`                    
- task5:`ViewModels`   
- task6:`Utilities + mainactivity -> shorouk saffan`
- task7:`Testing, Demo Video, and Final Merge to master`
