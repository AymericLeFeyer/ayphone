package fr.aylabs.ayphone.resume.ui.screens

import AyColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Business
import com.woowla.compose.icon.collections.remix.remix.Device
import com.woowla.compose.icon.collections.remix.remix.Logos
import com.woowla.compose.icon.collections.remix.remix.business.MailLine
import com.woowla.compose.icon.collections.remix.remix.device.PhoneLine
import com.woowla.compose.icon.collections.remix.remix.logos.GithubLine
import com.woowla.compose.icon.collections.remix.remix.logos.LinkedinLine
import fr.aylabs.ayphone.resume.domain.models.Resume
import fr.aylabs.ayphone.resume.ui.viewmodels.ResumeViewModel
import kotlinx.coroutines.launch

@Composable
fun ResumeScreenReady(
    resume: Resume,
    vm: ResumeViewModel,
    onMissionClick: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    val requestedTabIndex by vm.requestedTabIndex.collectAsStateWithLifecycle()

    val tabs = remember {
        listOf(
            "Missions",
            "Compétences",
        )
    }
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
    )

    LaunchedEffect(requestedTabIndex) {
        val tabIndex = requestedTabIndex ?: return@LaunchedEffect
        pagerState.animateScrollToPage(tabIndex)
        vm.consumeTabRequest()
    }

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(AyColors.ContainerQuiet, RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            Text(
                text = resume.name,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = resume.role,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 8.dp),
            ) {
                IconButton(onClick = { uriHandler.openUri("mailto:${resume.contacts.email}") }) {
                    Icon(Remix.Business.MailLine, contentDescription = "Email")
                }
                IconButton(onClick = { uriHandler.openUri("tel:${resume.contacts.phone}") }) {
                    Icon(Remix.Device.PhoneLine, contentDescription = "Phone")
                }
                IconButton(onClick = { uriHandler.openUri(resume.contacts.linkedin) }) {
                    Icon(Remix.Logos.LinkedinLine, contentDescription = "LinkedIn")
                }
                IconButton(onClick = { uriHandler.openUri(resume.contacts.github) }) {
                    Icon(Remix.Logos.GithubLine, contentDescription = "GitHub")
                }
            }
        }

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
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ResumeScreenMissions(
                    resume = resume,
                    vm = vm,
                    onMissionClick = onMissionClick,
                )
                1 -> ResumeScreenSkills(
                    resume = resume,
                    vm = vm,
                )
            }
        }
    }
}
