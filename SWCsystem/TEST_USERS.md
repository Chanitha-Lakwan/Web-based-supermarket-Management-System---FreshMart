# Test Users for Email-Based Redirection

## How to Test the Email-Based Redirection Feature

### 1. Regular User (FreshMart Page)
Register with any email that does NOT contain "fresh.com":
- **Username**: `john_doe`
- **Email**: `john@gmail.com`
- **Password**: `password123`
- **First Name**: `John`
- **Last Name**: `Doe`

**Expected Result**: After login → Redirected to `/freshmart` (FreshMart page)

### 2. Fresh.com User (Products&Categories Page)
Register with an email containing "fresh.com":
- **Username**: `manager_fresh`
- **Email**: `manager@fresh.com`
- **Password**: `password123`
- **First Name**: `Fresh`
- **Last Name**: `Manager`

**Expected Result**: After login → Redirected to `/products-categories` (Products&Categories page)

### 3. Alternative Fresh.com User
- **Username**: `admin_fresh`
- **Email**: `admin@fresh.com`
- **Password**: `password123`
- **First Name**: `Admin`
- **Last Name**: `User`

**Expected Result**: After login → Redirected to `/products-categories` (Products&Categories page)

## Testing Steps

1. **Start the application**: `./mvnw spring-boot:run`
2. **Access**: `http://localhost:8080`
3. **Register** one of the test users above
4. **Login** with the credentials
5. **Verify** the correct page is displayed based on email domain

## Features Available

### FreshMart Page (Regular Users)
- Product browsing interface
- Shopping cart functionality
- User name display in header
- Logout and dashboard links

### Products&Categories Page (Fresh.com Users)
- Product management interface
- Category management
- User name display in header
- Links to FreshMart and logout

## Email Domain Logic

The system checks if the username (which can be an email when users login with email) contains "fresh.com":
- ✅ Login with `manager@fresh.com` → Products&Categories
- ✅ Login with `admin@fresh.com` → Products&Categories
- ✅ Login with `test@subdomain.fresh.com` → Products&Categories
- ❌ Login with `manager@gmail.com` → FreshMart
- ❌ Login with `admin@yahoo.com` → FreshMart
- ❌ Login with `user@freshmart.com` → FreshMart (doesn't contain "fresh.com")

**Note**: Users can login with either their username OR email address. The system will check the login identifier for "fresh.com" content.

