import ReactTyped from "react-typed";

interface ISkillWritingProps {
    fontSize?: number,
    skills: string[]
}

function SkillWriting ({ fontSize, skills }: ISkillWritingProps) {
    return (
        <p style={{ fontSize: `${fontSize ?? 18}px` }}>
            <span style={{ color: "#ffffff80" }}>Desenvolvedor </span>
            <ReactTyped strings={skills} typeSpeed={150} backSpeed={150} loopCount={2000} loop />
        </p>
    )
}

export default SkillWriting;