export default function TeamProjectCard({ title, period, role, description, highlights, tags, links }) {
  return <article className="erp-project-card">
    <div className="erp-project-heading">
      <h3>{title}</h3>
      {period && <p className="erp-project-period">{period}</p>}
    </div>
    <p className="erp-project-role">{role}</p>
    <p className="erp-project-description">{description}</p>
    {highlights.length > 0 && <ul className="erp-project-contributions">
      {highlights.map(item => <li key={item}>{item}</li>)}
    </ul>}
    {tags.length > 0 && <div className="tags erp-project-tags">
      {tags.map(tag => <span key={tag}>{tag}</span>)}
    </div>}
    {links.length > 0 && <div className="project-links">
      {links.filter(link => /^https?:\/\//i.test(link.url)).map(link => <a href={link.url} key={link.url} target="_blank" rel="noopener noreferrer">{link.label} <span aria-hidden="true">↗</span></a>)}
    </div>}
  </article>;
}
