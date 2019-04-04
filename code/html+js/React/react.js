// import {DataPicker} from 'antd';
const element = <h1>Hello, world!</h1>;
ReactDOM.render(
   element,
   document.getElementById('example')
);

function FormattedDate(props) {
  return <h2>现在是 {props.date.toLocaleTimeString()}.</h2>;
}

class Clock extends React.Component {
    constructor(props){
        super(props);
        this.state = {date: new Date};
    }
    componentDidMount() {
      this.timerID = setInterval(
        () => this.tick(),
        1000
      );
    }

    componentWillUnmount() {
      clearInterval(this.timerID);
    }

    tick() {
      this.setState({
        date: new Date()
      });
    }

  render() {
    return (
      <div>
        <h1>Hello, world!</h1>
        <h2>现在是 {this.state.date.toLocaleTimeString()}.</h2>
        <FormattedDate date={this.state.date} />
      </div>
    );
  }
}

ReactDOM.render(
    <Clock />,
    document.getElementById('clock')
);
