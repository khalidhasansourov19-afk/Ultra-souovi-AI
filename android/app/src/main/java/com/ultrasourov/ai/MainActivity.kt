package com.ultrasourov.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.ultrasourov.ai.data.AppLanguage
import com.ultrasourov.ai.ui.screens.*
import com.ultrasourov.ai.ui.theme.HiOSDarkBackground
import com.ultrasourov.ai.ui.theme.UltraSourovAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UltraSourovAITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = HiOSDarkBackground
                ) {
                    val navController = rememberNavController()
                    var currentLanguage by remember { mutableStateOf(AppLanguage.BENGALI) }
                    var dynamicPortStatus by remember { mutableStateOf("Ultra Sourov AI • Online") }

                    NavHost(navController = navController, startDestination = "voice_assistant") {
                        composable("voice_assistant") {
                            VoiceAssistantScreen(language = currentLanguage, onStatusUpdate = { dynamicPortStatus = it })
                        }
                        composable("vision_solver") {
                            VisionSolverScreen(language = currentLanguage, onStatusUpdate = { dynamicPortStatus = it })
                        }
                        composable("device_control") {
                            DeviceControlScreen(onStatusUpdate = { dynamicPortStatus = it })
                        }
                        composable("security_shield") {
                            SecurityShieldScreen(onStatusUpdate = { dynamicPortStatus = it })
                        }
                        composable("media_editor") {
                            MediaEditorScreen(onStatusUpdate = { dynamicPortStatus = it })
                        }
                        composable("universal_solver") {
                            UniversalSolverScreen(language = currentLanguage, onStatusUpdate = { dynamicPortStatus = it })
                        }
                    }
                }
            }
        }
    }
}
