import { capabilities, overseasExperience, resumeStrengths } from './data';

export default function ResumeDetails() {
  return <section className="section resume-section" aria-labelledby="resume-title">
    <div className="container">
      <div className="section-title"><p className="eyebrow">EXPERIENCE & CAPABILITIES</p><h2 id="resume-title">경험에서 쌓아온 역량</h2><p className="section-description">현장에서 배운 소통과 책임감에, 개발 교육에서 익힌 기술을 더합니다.</p></div>
      <div className="strength-grid">{resumeStrengths.map((strength, index) => <article className="strength-card" key={strength.title}><span className="strength-number">0{index + 1}</span><p className="eyebrow">{strength.label}</p><h3>{strength.title}</h3><p>{strength.description}</p></article>)}</div>
      <article className="overseas-card"><div><p className="eyebrow">OVERSEAS FIELD EXPERIENCE</p><h3>{overseasExperience.location}</h3><p className="period">{overseasExperience.period}</p></div><div><h4>{overseasExperience.title}</h4><p>{overseasExperience.description}</p></div></article>
      <div className="capability-heading"><h3>기술을 이렇게 활용할 수 있습니다</h3><p>교육·프로젝트 과정에서 익힌 역량입니다. 항목을 열어 자세히 볼 수 있습니다.</p></div>
      <div className="capability-list">{capabilities.map((capability, index) => <details className="capability" key={capability.title} open={index === 0}><summary><span><span className="capability-number">0{index + 1}</span>{capability.title}</span><span className="capability-toggle" aria-hidden="true">+</span></summary><div className="capability-body"><ul>{capability.items.map(item => <li key={item}>{item}</li>)}</ul><div className="tags">{capability.tags.map(tag => <span key={tag}>{tag}</span>)}</div></div></details>)}</div>
    </div>
  </section>;
}
