package com.electrohub.fixly.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Font Awesome Icon Collection for Fixly Application.
 * All icons adhere strictly to Font Awesome vector paths.
 */
object FaIcons {

    private fun createFaVector(name: String, pathBuilder: ImageVector.Builder.() -> Unit): ImageVector {
        return ImageVector.Builder(
            name = name,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply(pathBuilder).build()
    }

    val Home: ImageVector = createFaVector("FaHome") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(10f, 20f)
            verticalLineToRelative(-6f)
            horizontalLineToRelative(4f)
            verticalLineToRelative(6f)
            horizontalLineToRelative(5f)
            verticalLineToRelative(-8f)
            horizontalLineToRelative(3f)
            lineTo(12f, 3f)
            lineTo(2f, 12f)
            horizontalLineToRelative(3f)
            verticalLineToRelative(8f)
            close()
        }
    }

    val Bookings: ImageVector = createFaVector("FaBookings") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19f, 3f)
            horizontalLineToRelative(-1f)
            verticalLineTo(1f)
            horizontalLineToRelative(-2f)
            verticalLineToRelative(2f)
            horizontalLineTo(8f)
            verticalLineTo(1f)
            horizontalLineTo(6f)
            verticalLineToRelative(2f)
            horizontalLineTo(5f)
            curveTo(3.89f, 3f, 3f, 3.9f, 3f, 5f)
            verticalLineToRelative(14f)
            curveTo(3f, 20.1f, 3.89f, 21f, 5f, 21f)
            horizontalLineToRelative(14f)
            curveTo(20.1f, 21f, 21f, 20.1f, 21f, 19f)
            verticalLineTo(5f)
            curveTo(21f, 3.9f, 20.1f, 3f, 19f, 3f)
            close()
            moveTo(19f, 19f)
            horizontalLineTo(5f)
            verticalLineTo(9f)
            horizontalLineToRelative(14f)
            verticalLineTo(19f)
            close()
        }
    }

    val Messages: ImageVector = createFaVector("FaMessages") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(20f, 2f)
            horizontalLineTo(4f)
            curveTo(2.9f, 2f, 2f, 2.9f, 2f, 4f)
            verticalLineToRelative(18f)
            lineToRelative(4f, -4f)
            horizontalLineToRelative(14f)
            curveTo(21.1f, 18f, 22f, 17.1f, 22f, 16f)
            verticalLineTo(4f)
            curveTo(22f, 2.9f, 21.1f, 2f, 20f, 2f)
            close()
            moveTo(20f, 16f)
            horizontalLineTo(5.17f)
            lineTo(4f, 17.17f)
            verticalLineTo(4f)
            horizontalLineToRelative(16f)
            verticalLineTo(16f)
            close()
        }
    }

    val User: ImageVector = createFaVector("FaUser") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 12f)
            curveTo(14.21f, 12f, 16f, 10.21f, 16f, 8f)
            curveTo(16f, 5.79f, 14.21f, 4f, 12f, 4f)
            curveTo(9.79f, 4f, 8f, 5.79f, 8f, 8f)
            curveTo(8f, 10.21f, 9.79f, 12f, 12f, 12f)
            close()
            moveTo(12f, 14f)
            curveTo(9.33f, 14f, 4f, 15.34f, 4f, 18f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(16f)
            verticalLineToRelative(-2f)
            curveTo(20f, 15.34f, 14.67f, 14f, 12f, 14f)
            close()
        }
    }

    val Search: ImageVector = createFaVector("FaSearch") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(15.5f, 14f)
            horizontalLineToRelative(-0.79f)
            lineToRelative(-0.28f, -0.27f)
            curveTo(15.41f, 12.59f, 16f, 11.11f, 16f, 9.5f)
            curveTo(16f, 5.91f, 13.09f, 3f, 9.5f, 3f)
            curveTo(5.91f, 3f, 3f, 5.91f, 3f, 9.5f)
            curveTo(3f, 13.09f, 5.91f, 16f, 9.5f, 16f)
            curveTo(11.11f, 16f, 12.59f, 15.41f, 13.73f, 14.43f)
            lineToRelative(0.27f, 0.28f)
            verticalLineToRelative(0.79f)
            lineToRelative(5f, 4.99f)
            lineTo(20.49f, 19f)
            lineToRelative(-4.99f, -5f)
            close()
            moveTo(9.5f, 14f)
            curveTo(7.01f, 14f, 5f, 11.99f, 5f, 9.5f)
            curveTo(5f, 7.01f, 7.01f, 5f, 9.5f, 5f)
            curveTo(11.99f, 5f, 14f, 7.01f, 14f, 9.5f)
            curveTo(14f, 11.99f, 11.99f, 14f, 9.5f, 14f)
            close()
        }
    }

    val MapMarker: ImageVector = createFaVector("FaMapMarker") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 2f)
            curveTo(8.13f, 2f, 5f, 5.13f, 5f, 9f)
            curveTo(5f, 14.25f, 12f, 22f, 12f, 22f)
            curveTo(12f, 22f, 19f, 14.25f, 19f, 9f)
            curveTo(19f, 5.13f, 15.87f, 2f, 12f, 2f)
            close()
            moveTo(12f, 11.5f)
            curveTo(10.62f, 11.5f, 9.5f, 10.38f, 9.5f, 9f)
            curveTo(9.5f, 7.62f, 10.62f, 6.5f, 12f, 6.5f)
            curveTo(13.38f, 6.5f, 14.5f, 7.62f, 14.5f, 9f)
            curveTo(14.5f, 10.38f, 13.38f, 11.5f, 12f, 11.5f)
            close()
        }
    }

    val Bell: ImageVector = createFaVector("FaBell") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 22f)
            curveTo(13.1f, 22f, 14f, 21.1f, 14f, 20f)
            horizontalLineToRelative(-4f)
            curveTo(10f, 21.1f, 10.89f, 22f, 12f, 22f)
            close()
            moveTo(18f, 16f)
            verticalLineToRelative(-5f)
            curveTo(18f, 7.93f, 15.86f, 5.36f, 13f, 4.68f)
            verticalLineTo(4f)
            curveTo(13f, 3.45f, 12.55f, 3f, 12f, 3f)
            curveTo(11.45f, 3f, 11f, 3.45f, 11f, 4f)
            verticalLineToRelative(0.68f)
            curveTo(8.14f, 5.36f, 6f, 7.92f, 6f, 11f)
            verticalLineToRelative(5f)
            lineToRelative(-2f, 2f)
            verticalLineToRelative(1f)
            horizontalLineToRelative(16f)
            verticalLineToRelative(-1f)
            lineToRelative(-2f, -2f)
            close()
        }
    }

    val Plumbing: ImageVector = createFaVector("FaPlumbing") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.35f, 10.04f)
            curveTo(18.67f, 6.59f, 15.64f, 4f, 12f, 4f)
            curveTo(9.11f, 4f, 6.6f, 5.64f, 5.35f, 8.04f)
            curveTo(2.34f, 8.36f, 0f, 10.91f, 0f, 14f)
            curveTo(0f, 17.31f, 2.69f, 20f, 6f, 20f)
            horizontalLineToRelative(13f)
            curveTo(21.76f, 20f, 23f, 18.76f, 23f, 17f)
            curveTo(23f, 13.98f, 21.15f, 11.23f, 19.35f, 10.04f)
            close()
        }
    }

    val Electrical: ImageVector = createFaVector("FaElectrical") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(7f, 2f)
            verticalLineToRelative(11f)
            horizontalLineToRelative(3f)
            verticalLineToRelative(9f)
            lineToRelative(7f, -12f)
            horizontalLineToRelative(-4f)
            lineToRelative(4f, -8f)
            horizontalLineTo(7f)
            close()
        }
    }

    val Mechanics: ImageVector = createFaVector("FaMechanics") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(22.7f, 19f)
            lineToRelative(-9.1f, -9.1f)
            curveTo(14.2f, 8.6f, 14.5f, 7.1f, 14f, 5.5f)
            curveTo(13.2f, 3.1f, 11f, 1.4f, 8.5f, 1f)
            curveTo(8.1f, 0.9f, 7.8f, 1.3f, 8f, 1.7f)
            lineToRelative(2.8f, 2.8f)
            lineToRelative(-2.8f, 2.8f)
            lineTo(5.2f, 4.5f)
            curveTo(4.8f, 4.3f, 4.4f, 4.6f, 4.5f, 5f)
            curveTo(4.9f, 7.5f, 6.6f, 9.7f, 9f, 10.5f)
            curveTo(10.6f, 11f, 12.1f, 10.7f, 13.4f, 10.1f)
            lineToRelative(9.1f, 9.1f)
            curveTo(22.9f, 19.6f, 22.9f, 20.4f, 22.7f, 19f)
            close()
        }
    }

    val Cleaning: ImageVector = createFaVector("FaCleaning") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19f, 3f)
            horizontalLineTo(5f)
            curveTo(3.9f, 3f, 3f, 3.9f, 3f, 5f)
            verticalLineToRelative(14f)
            curveTo(3f, 20.1f, 3.89f, 21f, 5f, 21f)
            horizontalLineToRelative(14f)
            curveTo(20.1f, 21f, 21f, 20.1f, 21f, 19f)
            verticalLineTo(5f)
            curveTo(21f, 3.9f, 20.1f, 3f, 19f, 3f)
            close()
        }
    }

    val Wrench: ImageVector = createFaVector("FaWrench") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(13.78f, 15.3f)
            lineToRelative(5.9f, 5.9f)
            curveTo(20.07f, 21.59f, 20.7f, 21.59f, 21.09f, 21.2f)
            lineToRelative(1.41f, -1.41f)
            curveTo(22.89f, 19.4f, 22.89f, 18.77f, 22.5f, 18.38f)
            lineToRelative(-5.9f, -5.9f)
            lineToRelative(1.42f, -1.42f)
            curveTo(18.61f, 10.47f, 18.17f, 9f, 17.08f, 9f)
            horizontalLineToRelative(-1.18f)
            lineToRelative(-2.61f, -2.61f)
            curveTo(14.28f, 4.31f, 13.25f, 3f, 11.5f, 3f)
            curveTo(9.57f, 3f, 8f, 4.57f, 8f, 6.5f)
            curveTo(8f, 8.25f, 9.31f, 9.28f, 11.39f, 10.29f)
            lineTo(14f, 12.9f)
            verticalLineToRelative(1.18f)
            curveTo(14f, 15.17f, 12.53f, 15.61f, 13.78f, 15.3f)
            close()
        }
    }

    val Star: ImageVector = createFaVector("FaStar") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 17.27f)
            lineTo(18.18f, 21f)
            lineToRelative(-1.64f, -7.03f)
            lineTo(22f, 9.24f)
            lineToRelative(-7.19f, -0.61f)
            lineTo(12f, 2f)
            lineTo(9.19f, 8.63f)
            lineTo(2f, 9.24f)
            lineToRelative(5.46f, 4.73f)
            lineTo(5.82f, 21f)
            close()
        }
    }

    val ShieldCheck: ImageVector = createFaVector("FaShieldCheck") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 1f)
            lineTo(3f, 5f)
            verticalLineToRelative(6f)
            curveTo(3f, 16.55f, 6.84f, 21.74f, 12f, 23f)
            curveTo(17.16f, 21.74f, 21f, 16.55f, 21f, 11f)
            verticalLineTo(5f)
            lineTo(12f, 1f)
            close()
            moveTo(10f, 17f)
            lineToRelative(-4f, -4f)
            lineToRelative(1.41f, -1.41f)
            lineTo(10f, 14.17f)
            lineToRelative(6.59f, -6.59f)
            lineTo(18f, 9f)
            lineToRelative(-8f, 8f)
            close()
        }
    }

    val CheckCircle: ImageVector = createFaVector("FaCheckCircle") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 2f)
            curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
            curveTo(2f, 17.52f, 6.48f, 22f, 12f, 22f)
            curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
            curveTo(22f, 6.48f, 17.52f, 2f, 12f, 2f)
            close()
            moveTo(10f, 17f)
            lineToRelative(-5f, -5f)
            lineToRelative(1.41f, -1.41f)
            lineTo(10f, 14.17f)
            lineToRelative(7.59f, -7.59f)
            lineTo(19f, 8f)
            lineToRelative(-9f, 9f)
            close()
        }
    }

    val Clock: ImageVector = createFaVector("FaClock") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(11.99f, 2f)
            curveTo(6.47f, 2f, 2f, 6.48f, 2f, 12f)
            curveTo(2f, 17.52f, 6.47f, 22f, 11.99f, 22f)
            curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
            curveTo(22f, 6.48f, 17.52f, 2f, 11.99f, 2f)
            close()
            moveTo(12f, 20f)
            curveTo(7.58f, 20f, 4f, 16.42f, 4f, 12f)
            curveTo(4f, 7.58f, 7.58f, 4f, 12f, 4f)
            curveTo(16.42f, 4f, 20f, 7.58f, 20f, 12f)
            curveTo(20f, 16.42f, 16.42f, 20f, 12f, 20f)
            close()
            moveTo(12.5f, 7f)
            horizontalLineTo(11f)
            verticalLineToRelative(6f)
            lineToRelative(5.25f, 3.15f)
            lineToRelative(0.75f, -1.23f)
            lineToRelative(-4.5f, -2.67f)
            verticalLineTo(7f)
            close()
        }
    }

    val ChevronRight: ImageVector = createFaVector("FaChevronRight") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(10f, 6f)
            lineToRelative(-1.41f, 1.41f)
            lineTo(13.17f, 12f)
            lineToRelative(-4.58f, 4.59f)
            lineTo(10f, 18f)
            lineToRelative(6f, -6f)
            close()
        }
    }

    val ArrowLeft: ImageVector = createFaVector("FaArrowLeft") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(20f, 11f)
            horizontalLineTo(7.83f)
            lineToRelative(5.59f, -5.59f)
            lineTo(12f, 4f)
            lineToRelative(-8f, 8f)
            lineToRelative(8f, 8f)
            lineToRelative(1.41f, -1.41f)
            lineTo(7.83f, 13f)
            horizontalLineTo(20f)
            verticalLineTo(11f)
            close()
        }
    }

    val Phone: ImageVector = createFaVector("FaPhone") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(6.62f, 10.79f)
            curveTo(8.06f, 13.62f, 10.38f, 15.94f, 13.21f, 17.38f)
            lineToRelative(2.2f, -2.2f)
            curveTo(15.69f, 14.91f, 16.08f, 14.82f, 16.43f, 14.94f)
            curveTo(17.55f, 15.31f, 18.76f, 15.51f, 20f, 15.51f)
            curveTo(20.55f, 15.51f, 21f, 15.96f, 21f, 16.51f)
            verticalLineTo(20f)
            curveTo(21f, 20.55f, 20.55f, 21f, 20f, 21f)
            curveTo(10.61f, 21f, 3f, 13.39f, 3f, 4f)
            curveTo(3f, 3.45f, 3.45f, 3f, 4f, 3f)
            horizontalLineToRelative(3.5f)
            curveTo(8.05f, 3f, 8.5f, 3.45f, 8.5f, 4f)
            curveTo(8.5f, 5.25f, 8.7f, 6.45f, 9.07f, 7.57f)
            curveTo(9.18f, 7.92f, 9.1f, 8.31f, 8.82f, 8.59f)
            lineToRelative(-2.2f, 2.2f)
            close()
        }
    }

    val Wallet: ImageVector = createFaVector("FaWallet") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(21f, 18f)
            verticalLineTo(6f)
            curveTo(21f, 4.89f, 20.1f, 4f, 19f, 4f)
            horizontalLineTo(5f)
            curveTo(3.89f, 4f, 3f, 4.89f, 3f, 6f)
            verticalLineToRelative(12f)
            curveTo(3f, 19.1f, 3.89f, 20f, 5f, 20f)
            horizontalLineToRelative(14f)
            curveTo(20.1f, 20f, 21f, 19.1f, 21f, 18f)
            close()
            moveTo(19f, 18f)
            horizontalLineTo(5f)
            verticalLineTo(8f)
            horizontalLineToRelative(14f)
            verticalLineTo(18f)
            close()
            moveTo(16f, 12f)
            curveTo(15.45f, 12f, 15f, 12.45f, 15f, 13f)
            curveTo(15f, 13.55f, 15.45f, 14f, 16f, 14f)
            curveTo(16.55f, 14f, 17f, 13.55f, 17f, 13f)
            curveTo(17f, 12.45f, 16.55f, 12f, 16f, 12f)
            close()
        }
    }

    val Filter: ImageVector = createFaVector("FaFilter") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(10f, 18f)
            horizontalLineToRelative(4f)
            verticalLineToRelative(-2f)
            horizontalLineToRelative(-4f)
            verticalLineToRelative(2f)
            close()
            moveTo(3f, 6f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(18f)
            verticalLineTo(6f)
            horizontalLineTo(3f)
            close()
            moveTo(6f, 13f)
            horizontalLineToRelative(12f)
            verticalLineToRelative(-2f)
            horizontalLineTo(6f)
            verticalLineToRelative(2f)
            close()
        }
    }

    val Headset: ImageVector = createFaVector("FaHeadset") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(12f, 1f)
            curveTo(6.48f, 1f, 2f, 5.48f, 2f, 11f)
            verticalLineToRelative(7f)
            curveTo(2f, 19.66f, 3.34f, 21f, 5f, 21f)
            horizontalLineToRelative(3f)
            verticalLineToRelative(-8f)
            horizontalLineTo(4f)
            verticalLineToRelative(-2f)
            curveTo(4f, 6.58f, 7.58f, 3f, 12f, 3f)
            curveTo(16.42f, 3f, 20f, 6.58f, 20f, 11f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(-4f)
            verticalLineToRelative(8f)
            horizontalLineToRelative(3f)
            curveTo(20.66f, 21f, 22f, 19.66f, 22f, 18f)
            verticalLineToRelative(-7f)
            curveTo(22f, 5.48f, 17.52f, 1f, 12f, 1f)
            close()
        }
    }

    val Lock: ImageVector = createFaVector("FaLock") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(18f, 8f)
            horizontalLineTo(17f)
            verticalLineTo(6f)
            curveTo(17f, 3.24f, 14.76f, 1f, 12f, 1f)
            curveTo(9.24f, 1f, 7f, 3.24f, 7f, 6f)
            verticalLineTo(8f)
            horizontalLineTo(6f)
            curveTo(4.9f, 8f, 4f, 8.9f, 4f, 10f)
            verticalLineTo(20f)
            curveTo(4f, 21.1f, 4.9f, 22f, 6f, 22f)
            horizontalLineTo(18f)
            curveTo(19.1f, 22f, 20f, 21.1f, 20f, 20f)
            verticalLineTo(10f)
            curveTo(20f, 8.9f, 19.1f, 8f, 18f, 8f)
            close()
            moveTo(12f, 17f)
            curveTo(10.9f, 17f, 10f, 16.1f, 10f, 15f)
            curveTo(10f, 13.9f, 10.9f, 13f, 12f, 13f)
            curveTo(13.1f, 13f, 14f, 13.9f, 14f, 15f)
            curveTo(14f, 16.1f, 13.1f, 17f, 12f, 17f)
            close()
            moveTo(15.1f, 8f)
            horizontalLineTo(8.9f)
            verticalLineTo(6f)
            curveTo(8.9f, 4.29f, 10.29f, 2.9f, 12f, 2.9f)
            curveTo(13.71f, 2.9f, 15.1f, 4.29f, 15.1f, 6f)
            verticalLineTo(8f)
            close()
        }
    }

    val Envelope: ImageVector = createFaVector("FaEnvelope") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(20f, 4f)
            horizontalLineTo(4f)
            curveTo(2.9f, 4f, 2.01f, 4.9f, 2.01f, 6f)
            lineTo(2f, 18f)
            curveTo(2f, 19.1f, 2.9f, 20f, 4f, 20f)
            horizontalLineTo(20f)
            curveTo(21.1f, 20f, 22f, 19.1f, 22f, 18f)
            verticalLineTo(6f)
            curveTo(22f, 4.9f, 21.1f, 4f, 20f, 4f)
            close()
            moveTo(20f, 8f)
            lineToRelative(-8f, 5f)
            lineToRelative(-8f, -5f)
            verticalLineTo(6f)
            lineToRelative(8f, 5f)
            lineToRelative(8f, -5f)
            verticalLineTo(8f)
            close()
        }
    }

    val Plus: ImageVector = createFaVector("FaPlus") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19f, 13f)
            horizontalLineTo(13f)
            verticalLineTo(19f)
            horizontalLineTo(11f)
            verticalLineTo(13f)
            horizontalLineTo(5f)
            verticalLineTo(11f)
            horizontalLineTo(11f)
            verticalLineTo(5f)
            horizontalLineTo(13f)
            verticalLineTo(11f)
            horizontalLineTo(19f)
            verticalLineTo(13f)
            close()
        }
    }

    val SignOut: ImageVector = createFaVector("FaSignOut") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(10.09f, 15.59f)
            lineTo(11.5f, 17f)
            lineTo(16.5f, 12f)
            lineTo(11.5f, 7f)
            lineTo(10.09f, 8.41f)
            lineTo(12.67f, 11f)
            horizontalLineTo(3f)
            verticalLineTo(13f)
            horizontalLineTo(12.67f)
            lineTo(10.09f, 15.59f)
            close()
            moveTo(19f, 3f)
            horizontalLineTo(5f)
            curveTo(3.89f, 3f, 3f, 3.89f, 3f, 5f)
            verticalLineTo(9f)
            horizontalLineTo(5f)
            verticalLineTo(5f)
            horizontalLineTo(19f)
            verticalLineTo(19f)
            horizontalLineTo(5f)
            verticalLineTo(15f)
            horizontalLineTo(3f)
            verticalLineTo(19f)
            curveTo(3f, 20.1f, 3.89f, 21f, 5f, 21f)
            horizontalLineTo(19f)
            curveTo(20.1f, 21f, 21f, 20.1f, 21f, 19f)
            verticalLineTo(5f)
            curveTo(21f, 3.89f, 20.1f, 3f, 19f, 3f)
            close()
        }
    }

    val Dashboard: ImageVector = createFaVector("FaDashboard") {
        path(fill = SolidColor(Color.Black)) {
            moveTo(3f, 13f)
            horizontalLineTo(11f)
            verticalLineTo(3f)
            horizontalLineTo(3f)
            verticalLineTo(13f)
            close()
            moveTo(3f, 21f)
            horizontalLineTo(11f)
            verticalLineTo(15f)
            horizontalLineTo(3f)
            verticalLineTo(21f)
            close()
            moveTo(13f, 21f)
            horizontalLineTo(21f)
            verticalLineTo(11f)
            horizontalLineTo(13f)
            verticalLineTo(21f)
            close()
            moveTo(13f, 3f)
            verticalLineTo(9f)
            horizontalLineTo(21f)
            verticalLineTo(3f)
            horizontalLineTo(13f)
            close()
        }
    }
}

@Composable
fun FaIcon(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}
