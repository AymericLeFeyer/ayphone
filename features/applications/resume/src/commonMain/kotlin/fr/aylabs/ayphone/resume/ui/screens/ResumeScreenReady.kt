package fr.aylabs.ayphone.resume.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.aylabs.ayphone.resume.domain.models.Resume
import kotlinx.coroutines.launch

@Composable
fun ResumeScreenReady(
    resume: Resume,
) {
    val coroutineScope = rememberCoroutineScope()

    val tabs = remember {
        listOf(
            "Missions",
            "Compétences",
        )
    }
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
    )

    Column {
        PrimaryTabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray),
                )
            },
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(tab) }
                )
            }
        }
        HorizontalPager(state = pagerState) {
            when (pagerState.currentPage) {
                0 -> ResumeScreenMissions(resume = resume)
                1 -> ResumeScreenSkills(resume = resume)
            }
        }
    }
}