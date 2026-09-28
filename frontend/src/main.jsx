import React, { useEffect, useState } from 'react';
import { createRoot } from 'react-dom/client';
import { profile, skillGroups, troubleshooting, awards, certificates } from './data';
import './styles.css';
import ResumeDetails from './ResumeDetails';
import TeamProjectCard from './TeamProjectCard';
import { projects } from './projects.js';
import { careers } from './careers.js';
import { skills as technologies } from './skills.js';



const navigation = [['about', '소개'], ['skills', '기술 스택'], ['projects', '프로젝트'], ['troubleshooting', '문제 해결'], ['career', '경력·자격']];
function SectionTitle({ label, title, description }) {
  return <div className="section-title"><p className="eyebrow">{label}</p><h2>{title}</h2>{description && <p className="section-description">{description}</p>}</div>;
}
function Tags({ items }) { return <div className="tags">{items.map(item => <span key={item}>{item}</span>)}</div>; }
function ProjectCard({ project, wide = false }) {
  if (project.category === 'TEAM') return <TeamProjectCard {...project} />;
  const isWorkProject = project.category === 'COMPANY' || project.category === 'FREELANCE';
  return <article className={`project-card ${wide ? 'wide' : ''} ${isWorkProject ? 'work-card' : ''}`}>
    {!isWorkProject && <div className="project-topline"><span className="project-category">{project.category}</span></div>}
    <div className="project-title-row"><h3>{project.title}</h3>{project.period && <span className="period">{project.period}</span>}</div>
    <p className="project-role">{project.role}</p><p className="project-description">{project.description}</p>
    {project.highlights.length > 0 && <ul className="highlights">{project.highlights.map(item => <li key={item}>{item}</li>)}</ul>}
    <Tags items={project.tags} />
    {project.links.length > 0 && <div className="project-links">{project.links.filter(link => /^https?:\/\//i.test(link.url)).map(link => <a href={link.url} key={link.url} target="_blank" rel="noopener noreferrer">{link.label} <span aria-hidden="true">↗</span></a>)}</div>}
  </article>;
}
function App() {

  const workProjects = projects.filter(item => item.category !== 'TEAM');
  const teamProjects = projects.filter(item => item.category === 'TEAM');

  const skills = skillGroups.map(group => ({ ...group, items: technologies.filter(item => item.category === group.category).map(item => item.name) })).filter(group => group.items.length > 0);
  const [menuOpen, setMenuOpen] = useState(false);
  const [active, setActive] = useState('');
  const [copyStatus, setCopyStatus] = useState('');
  useEffect(() => {
    const observer = new IntersectionObserver(entries => entries.forEach(entry => { if (entry.isIntersecting) setActive(entry.target.id); }), { rootMargin: '-15% 0px -60% 0px' });
    document.querySelectorAll('section[id]').forEach(section => observer.observe(section));
    const closeMenu = (event) => { if (event.key === 'Escape') setMenuOpen(false); };
    window.addEventListener('keydown', closeMenu);
    return () => { observer.disconnect(); window.removeEventListener('keydown', closeMenu); };
  }, []);
  async function copyEmail() {
    try { await navigator.clipboard.writeText(profile.email); setCopyStatus('이메일 주소를 복사했습니다.'); }
    catch { setCopyStatus(`주소를 직접 복사해주세요: ${profile.email}`); }
  }
  return <>
    <a className="skip-link" href="#main">본문으로 이동</a>
    <header className="header"><div className="container header-inner"><a className="brand" href="#" onClick={() => setMenuOpen(false)}>{profile.name}<span>.</span></a>
      <button className="menu-toggle" type="button" aria-expanded={menuOpen} aria-controls="main-navigation" aria-label={menuOpen ? '메뉴 닫기' : '메뉴 열기'} onClick={() => setMenuOpen(!menuOpen)}>{menuOpen ? '×' : '☰'}</button>
      <nav id="main-navigation" aria-label="주요 메뉴" className={menuOpen ? 'open' : ''}>{navigation.map(([id, label]) => <a href={`#${id}`} key={id} aria-current={active === id ? 'location' : undefined} onClick={() => setMenuOpen(false)}>{label}</a>)}<a className="nav-contact" href="#contact" onClick={() => setMenuOpen(false)}>연락하기 <span aria-hidden="true">↗</span></a></nav>
    </div></header>
    <main id="main">
      <section className="hero"><div className="container"><div className="intro-badge"><span />FULL-STACK DEVELOPER</div><h1>안녕하세요,<br />풀스택 개발자 <em>{profile.name}</em>입니다<span className="blue">.</span></h1><p className="hero-tagline">현장을 이해하고, 코드로 연결합니다.</p><p className="hero-description">고객과 가까웠던 경험을 바탕으로<br className="mobile-break" /> 화면부터 서버까지, 필요한 서비스를 만듭니다.</p><div className="hero-actions"><a className="button button-primary" href="#projects">프로젝트 보기 <span aria-hidden="true">↗</span></a><button className="button button-light email-copy" type="button" onClick={copyEmail} aria-label={`이메일 주소 복사: ${profile.email}`}>{profile.email} <span aria-hidden="true">⧉</span></button></div><p className="hero-copy-status" role="status" aria-live="polite">{copyStatus}</p>
      <div className="hero-facts"><div><strong>Full-stack<span>↗</span></strong><p>프론트엔드부터 백엔드까지</p></div><div><strong>Java & React</strong><p>화면과 서버를 연결하는 개발</p></div><div><strong>현장 → 개발</strong><p>고객 관점과 협업 경험</p></div></div></div></section>
      <section id="about" className="section"><div className="container"><SectionTitle label="ABOUT ME" title="저는 이런 개발자입니다" /><p className="about-copy">{profile.introduction}</p><div className="about-keywords"><span>고객의 관점에서</span><span>동료와 함께</span><span>끝까지 책임 있게</span></div></div></section>
      <section id="skills" className="section tinted"><div className="container"><SectionTitle label="TECH STACK" title="사용하는 기술" description="교육과 개발 과정에서 사용하고 경험한 기술입니다." /><div className="skills-grid">{skills.map(skill => <article className="skill-card" key={skill.title}><span className="skill-icon" aria-hidden="true">{skill.icon}</span><h3>{skill.title}</h3><p>{skill.description}</p><Tags items={skill.items} /></article>)}</div></div></section>
      <ResumeDetails />
      <section id="projects" className="section"><div className="container"><SectionTitle label="WORK & FREELANCE" title="실무 · 외주 프로젝트" description="회사에서의 개발 경험과 외주로 참여한 프로젝트를 소개합니다." /><div className="work-grid">{workProjects.map(project => <ProjectCard key={project.id} project={project} />)}</div></div></section>
      <section id="team-projects" className="section tinted"><div className="container"><SectionTitle label="TEAM PROJECTS" title="함께 만든 프로젝트" description="한국ICT인재개발원 Java 풀스택 과정에서 쌓은 팀 개발 경험입니다." /><div className="team-grid">{teamProjects.map(project => <ProjectCard key={project.id} project={project} wide />)}</div>{teamProjects.some(project => project.pending) && <p className="project-footnote"><span aria-hidden="true">↳</span> 프로젝트의 담당 역할과 구현 기능은 정리 후 추가할 예정입니다.</p>}</div></section>
      <section id="troubleshooting" className="section"><div className="container"><SectionTitle label="TROUBLESHOOTING" title="문제를 풀어가는 과정" description="무엇을 만들었는지와 함께, 어떻게 해결했는지를 기록합니다." />{troubleshooting.length ? <div className="trouble-list">{troubleshooting.map((item, index) => <article className="trouble-card" key={item.title}><span className="trouble-number">{String(index + 1).padStart(2, '0')}</span><div><span className="project-category">{item.project}</span><h3>{item.title}</h3><dl>{([['문제', item.problem], ['원인', item.cause], ['해결', item.solution], ['결과', item.result]]).map(([label, body]) => <div key={label}><dt>{label}</dt><dd>{body}</dd></div>)}</dl></div></article>)}</div> : <div className="trouble-empty"><div className="empty-icon" aria-hidden="true">⌘</div><div><span className="pending"><i />정리 중</span><h3>개발 중 마주한 문제와 해결 경험을 준비하고 있습니다.</h3><p>실제 프로젝트의 사례를 바탕으로 차근차근 채워나갈 예정입니다.</p><div className="process"><span>문제 발견</span><b>→</b><span>원인 분석</span><b>→</b><span>해결 과정</span><b>→</b><span>결과</span></div></div></div>}</div></section>
      <section id="career" className="section tinted"><div className="container"><SectionTitle label="CAREER & CERTIFICATES" title="경력과 배움의 기록" /><div className="career-grid"><div><h3 className="column-title">경력</h3><div className="career-list">{careers.map(career => <article className={`career-card ${career.current ? 'current' : ''}`} key={career.id}><p className="period">{career.period}{career.current && <span className="current-badge">재직 중</span>}</p><h4>{career.company}</h4><p className="career-role">{career.role}</p><p>{career.description}</p></article>)}</div></div><div><h3 className="column-title">교육 · 학력</h3><article className="education-card"><p className="period">2025.01 — 2025.08</p><h4>한국ICT인재개발원</h4><p>무중단 서비스를 위한 클라우드 기반<br />AI 활용 자바 풀스택 개발자 과정 수료</p></article><article className="education-card"><p className="period">2013 졸업</p><h4>구미전자공업고등학교</h4></article><h3 className="column-title spaced">수상</h3><Tags items={awards} /><h3 className="column-title spaced">자격증</h3><div className="certificate-list">{certificates.map(certificate => <article key={certificate.title}><h4>{certificate.title}</h4><p>{certificate.organization} · {certificate.date}</p></article>)}</div></div></div></div></section>
      <section id="contact" className="contact-section"><div className="container"><div className="contact-panel"><p className="eyebrow">LET’S CONNECT</p><h2>함께 만들 다음 이야기를 기다립니다.</h2><p>프로젝트와 협업에 관한 이야기를 편하게 보내주세요.</p><div className="contact-buttons"><a className="button button-white" href={`mailto:${profile.email}`}>메일 보내기 <span aria-hidden="true">↗</span></a><button className="copy-button" type="button" onClick={copyEmail}>이메일 복사 <span aria-hidden="true">⧉</span></button></div><a className="contact-email" href={`mailto:${profile.email}`}>{profile.email}</a><p className="copy-status" role="status" aria-live="polite">{copyStatus}</p></div></div></section>
    </main><footer className="container footer"><p>© {new Date().getFullYear()} {profile.name}. Built with React.</p><a href="#">맨 위로 <span aria-hidden="true">↑</span></a></footer>
  </>;
}
createRoot(document.getElementById('root')).render(<React.StrictMode><App /></React.StrictMode>);
