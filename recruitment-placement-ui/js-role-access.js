(function () {
  const role = sessionStorage.getItem('rpmsRole');
  const page = location.pathname.split('/').pop() || 'login.html';
  if (page !== 'login.html' && !role) {
    location.replace('login.html');
    return;
  }

  const features = {
    Admin: [
      ['View All Candidates','candidates.html','♙'],
      ['Register New Candidate','candidate-form.html','＋'],
      ['View All Companies','companies.html','▤'],
      ['Register New Company','companies.html#register-company','＋'],
      ['View Available Jobs','jobs.html','▣'],
      ['Create New Job Opening','job-form.html','＋'],
      ['Submit Job Application','application-form.html','≡'],
      ['List Applications','applications.html','☷'],
      ['Schedule Interview','interview-form.html','◷'],
      ['List Interviews','interviews.html','◷'],
      ['Add Interview Feedback','feedback-form.html','✎'],
      ['List Interview Feedback','feedback.html','☷'],
      ['Add Offer','offer-form.html','✉'],
      ['List Offers','offers.html','☷'],
      ['Add Placement','placement-form.html','✓'],
      ['List Placements','placements.html','☷'],
      ['Generate Recruitment Reports','reports.html','▥']
    ],
    Recruiter: [
      ['View Candidates','candidates.html','♙'],
      ['Register Candidates','candidate-form.html','＋'],
      ['View Companies','companies.html','▤'],
      ['Register Companies','companies.html#register-company','＋'],
      ['View Jobs','jobs.html','▣'],
      ['Create Job Openings','job-form.html','＋'],
      ['View Applications','applications.html','☷'],
      ['Manage Applications','applications.html','≡'],
      ['Schedule Interviews','interview-form.html','◷'],
      ['View Interviews','interviews.html','◷'],
      ['Add Interview Feedback','feedback-form.html','✎'],
      ['View Interview Feedback','feedback.html','☷'],
      ['Manage Offers','offers.html','✉'],
      ['Add Offer','offer-form.html','＋']
    ],
    'Placement Officer': [
      ['View Candidates','candidates.html','♙'],
      ['View Companies','companies.html','▤'],
      ['View Available Jobs','jobs.html','▣'],
      ['View Applications','applications.html','☷'],
      ['View Interview Details','interviews.html','◷'],
      ['View Interview Feedback','feedback.html','✎'],
      ['View Offers','offers.html','✉'],
      ['Add Placement','placement-form.html','＋'],
      ['View Placements','placements.html','✓'],
      ['Generate Placement Reports','reports.html','▥']
    ]
  };

  const allowedPages = {
    Admin: ['*'],
    Recruiter: ['dashboard.html','candidates.html','candidate-form.html','companies.html','jobs.html','job-form.html','applications.html','application-form.html','interviews.html','interview-form.html','feedback.html','feedback-form.html','offers.html','offer-form.html'],
    'Placement Officer': ['dashboard.html','candidates.html','companies.html','jobs.html','applications.html','interviews.html','feedback.html','offers.html','placement-form.html','placements.html','reports.html','placement-process.html']
  };
  const allowed = allowedPages[role] || [];
  if (page !== 'login.html' && page !== 'dashboard.html' &&
      !(role === 'Admin' && page === 'admin-menu.html') &&
      !allowed.includes('*') && !allowed.includes(page)) {
    location.replace('dashboard.html');
    return;
  }
  if (page === 'admin-menu.html' && role !== 'Admin') {
    location.replace('dashboard.html');
    return;
  }

  // Keep the existing sidebar design while limiting links to the signed-in role.
  const allowedFiles = new Set((features[role] || []).map(item => item[1].split('#')[0]));
  allowedFiles.add('dashboard.html');
  if (role === 'Admin') allowedFiles.add('admin-menu.html');
  document.querySelectorAll('.sidebar .nav a').forEach(a => {
    const href = (a.getAttribute('href') || '').split('#')[0];
    if (!allowedFiles.has(href)) a.remove();
  });
  document.querySelectorAll('.sidebar .nav .nav-label').forEach(label => {
    let next = label.nextElementSibling, hasLink = false;
    while (next && !next.classList.contains('nav-label')) {
      if (next.matches('a')) hasLink = true;
      next = next.nextElementSibling;
    }
    if (!hasLink) label.remove();
  });
  // Remove any legacy terminal-style numeric prefixes from remaining navigation.
  document.querySelectorAll('.sidebar .nav a').forEach(a => {
    a.childNodes.forEach(node => {
      if (node.nodeType === Node.TEXT_NODE) node.textContent = node.textContent.replace(/^\\s*\\d+\\.\\s*/, '');
    });
  });

  document.querySelectorAll('.topbar .role').forEach(el => el.textContent = role + ' Workspace');
  const profile = document.querySelector('.topbar .profile');
  if (profile) {
    const name = profile.querySelector('b');
    const subtitle = profile.querySelector('small');
    const avatar = profile.querySelector('.avatar');
    if (name) name.textContent = role === 'Admin' ? 'Admin User' : role === 'Recruiter' ? 'Recruiter User' : 'Placement Officer';
    if (subtitle) subtitle.textContent = role;
    if (avatar) avatar.textContent = role === 'Admin' ? 'AD' : role === 'Recruiter' ? 'RC' : 'PO';
  }

  // Add role-specific quick-access cards to the existing dashboard.
  if (page === 'dashboard.html') {
    const content = document.querySelector('.content');
    const pageHead = content && content.querySelector('.page-head');
    if (content && pageHead && !document.getElementById('role-feature-grid')) {
      const heading = document.createElement('div');
      heading.className = 'card-head';
      heading.innerHTML = '<div><h2>Workspace Features</h2><div class="card-sub">Quick access to the modules available for your role</div></div>';
      const card = document.createElement('div');
      card.className = 'card';
      card.style.marginBottom = '18px';
      card.appendChild(heading);
      const grid = document.createElement('div');
      grid.id = 'role-feature-grid';
      grid.className = 'grid three-col';
      grid.style.marginTop = '14px';
      (features[role] || []).forEach(([label, href, icon]) => {
        const link = document.createElement('a');
        link.className = 'card';
        link.href = href;
        link.style.cssText = 'display:block;color:var(--ink);margin:0;text-decoration:none';
        const top = document.createElement('div');
        top.className = 'stat-top';
        const symbol = document.createElement('span');
        symbol.className = 'stat-icon';
        symbol.textContent = icon;
        top.appendChild(symbol);
        const title = document.createElement('h2');
        title.style.cssText = 'margin:14px 0 5px;font-size:15px';
        title.textContent = label;
        const hint = document.createElement('p');
        hint.className = 'muted';
        hint.style.cssText = 'font-size:12px;margin:0';
        hint.textContent = 'Open module';
        link.append(top, title, hint);
        grid.appendChild(link);
      });
      card.appendChild(grid);
      pageHead.insertAdjacentElement('afterend', card);
    }
    const h1 = content && content.querySelector('.page-head h1');
    if (h1) h1.textContent = role + ' Dashboard';
    const intro = content && content.querySelector('.page-head p');
    if (intro) intro.textContent = 'Welcome to your ' + role.toLowerCase() + ' workspace. Access the features available for your role below.';
    // Hide placement-only summary panels from Recruiters, and hiring-only panels from Placement Officers.
    if (role === 'Recruiter') {
      document.querySelectorAll('.content .card').forEach(c => {
        const heading = c.querySelector('h2')?.textContent.trim().toLowerCase();
        if (heading === 'recent placements') c.style.display = 'none';
      });
      const placedStat = [...document.querySelectorAll('.stat')].find(s => s.innerText.toLowerCase().includes('students placed'));
      if (placedStat) placedStat.style.display = 'none';
    }
    if (role === 'Placement Officer') {
      document.querySelectorAll('.content .card').forEach(c => {
        const heading = c.querySelector('h2')?.textContent.trim().toLowerCase();
        if (heading === 'recruitment funnel' || heading === 'recent job openings' || heading === 'quick actions') c.style.display = 'none';
      });
      const activeJobs = [...document.querySelectorAll('.stat')].find(s => s.innerText.toLowerCase().includes('active jobs'));
      if (activeJobs) activeJobs.style.display = 'none';
    }
  }

  document.querySelectorAll('a.logout').forEach(a => {
    a.addEventListener('click', () => sessionStorage.removeItem('rpmsRole'));
  });
})();
