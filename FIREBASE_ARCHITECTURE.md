# Fixly Firebase Architecture & Configuration Guide

This document defines the backend platform configuration and Cloud Firestore data schema prepared for Phase 2 integration.

---

## 1. Firebase Overview & Required Services
- **Firebase Authentication**: Email/Password and Phone Auth (UG +256 format).
- **Cloud Firestore**: Primary NoSQL database storing user profiles, service categories, providers, bookings, quotations, messages, notifications, and wallet transactions.
- **Cloud Storage**: File storage for provider verification documents, business logos, portfolio images, and chat attachments.
- **Cloud Functions (TypeScript)**: Trusted business logic for booking status transitions, quotation workflows, notification dispatches, and commission fee calculations.
- **Firebase Cloud Messaging (FCM)**: Push notification delivery for booking updates, chat messages, and payment alerts.

---

## 2. Cloud Firestore Schema Structure

```
/users/{userId}
  - id: string
  - fullName: string
  - email: string
  - phoneNumber: string
  - role: "CUSTOMER" | "PROVIDER" | "ADMIN"
  - isVerified: boolean
  - defaultAddress: string
  - createdAt: timestamp

/providerProfiles/{providerId}
  - id: string
  - userId: string
  - businessName: string
  - categoryId: string
  - categoryName: string
  - rating: number
  - reviewCount: number
  - isVerified: boolean
  - distanceKm: number
  - startingPriceUGX: number
  - phone: string
  - address: string
  - aboutText: string

/providerProfiles/{providerId}/services/{serviceId}
  - id: string
  - name: string
  - description: string
  - priceUGX: number

/bookings/{bookingId}
  - id: string
  - customerId: string
  - providerId: string
  - providerName: string
  - categoryName: string
  - serviceTitle: string
  - dateString: string
  - timeString: string
  - status: "Pending" | "Confirmed" | "Completed" | "Cancelled"
  - address: string
  - amountUGX: number
  - notes: string
  - createdAt: timestamp

/conversations/{conversationId}/messages/{messageId}
  - id: string
  - senderId: string
  - senderName: string
  - text: string
  - timestamp: timestamp
  - isFromUser: boolean

/notifications/{notificationId}
  - id: string
  - userId: string
  - title: string
  - message: string
  - timeAgo: string
  - type: string
  - isRead: boolean

/walletTransactions/{txId}
  - id: string
  - userId: string
  - title: string
  - date: string
  - amountUGX: number
  - isCredit: boolean
```

---

## 3. Required Steps to Connect Firebase in Phase 2

1. Create a Firebase Project at [https://console.firebase.google.com/](https://console.firebase.google.com/) named `Fixly`.
2. Register the Android app with package name `com.electrohub.fixly`.
3. Download `google-services.json` and place it under `/Users/brizeadmin/My/Fixly/app/google-services.json`.
4. Apply `com.google.gms.google-services` plugin in `app/build.gradle.kts`.
5. Enable **Email/Password** in Firebase Authentication Console.
6. Create Cloud Firestore database in Native Mode (location e.g. `europe-west1` or `us-central`).
7. Deploy Security Rules and Cloud Functions.
