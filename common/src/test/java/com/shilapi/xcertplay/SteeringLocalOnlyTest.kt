package com.shilapi.xcertplay

import android.app.job.JobScheduler
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33], manifest = Config.NONE)
class SteeringLocalOnlyTest {
    @Test fun savingAndRestoringNeverCreatesUploadWork() {
        val context = RuntimeEnvironment.getApplication()
        val jobs = context.getSystemService(JobScheduler::class.java)
        jobs.cancelAll()
        val profile = SteeringProfile("Test vehicle", "Test head unit", bindings = listOf(
            SteeringBinding("play_pause", 85, 0, source = "oneos"),
        ))
        SteeringProfiles.save(context, profile)
        assertEquals(profile, SteeringProfiles.loadEnabled(context))
        assertTrue(jobs.allPendingJobs.isEmpty())
        assertFalse(File(context.filesDir, "steering-profiles/outbox").exists())
        SteeringProfiles.disable(context)
        assertNull(SteeringProfiles.loadEnabled(context))
        assertEquals(profile, SteeringProfiles.load(context))
        assertTrue(jobs.allPendingJobs.isEmpty())
    }
}
