
//https://www.zhihu.com/question/20756479
//参考：用计算机算圆周率，是个怎样的过程？
#include<iostream>
#include<iomanip>
#include<string>
#include<conio.h>
#include<Windows.h>
#include<thread>
using namespace std;
namespace MultiPrecision {
	const int PREC = 50000;//!!!
	class Decimal {
	public:
		Decimal();
		Decimal(double x);
		// p (p > 0) is the number of digits after the decimal point
		std::string to_string(int p) const;
		bool is_zero()const {
			if (integer)return 0;
			for (int i = 0; i<len; i++)if (data[i])return 0;
			return 1;
		}

		friend Decimal operator + (const Decimal &a, const Decimal &b);
		friend Decimal operator + (const Decimal &a, int x);
		friend Decimal operator + (int x, const Decimal &a);
		friend Decimal operator + (const Decimal &a, long long x);
		friend Decimal operator + (long long x, const Decimal &a);
		friend Decimal operator - (const Decimal &a, const Decimal &b);
		friend Decimal operator - (const Decimal &a, int x);
		friend Decimal operator - (int x, const Decimal &a);
		friend Decimal operator - (const Decimal &a, long long x);
		friend Decimal operator - (long long x, const Decimal &a);

		friend Decimal operator * (Decimal a, int x);

		friend Decimal operator / (const Decimal &a, int x);

		friend bool operator >= (const Decimal &a, const Decimal &b);
		friend bool operator == (const Decimal &a, const Decimal &b);

		Decimal & operator += (int x);
		Decimal & operator += (long long x);
		Decimal & operator += (const Decimal &b);
		Decimal & operator -= (int x);
		Decimal & operator -= (long long x);
		Decimal & operator -= (const Decimal &b);

		Decimal & operator *= (int x);

		Decimal & operator /= (int x);

		friend Decimal operator - (const Decimal &a);

	private:
		static const int len = PREC / 9 + 1;
		static const int mo = 1000000000;

		static void append_to_string(std::string &s, long long x);

		bool is_neg;
		long long integer;
		int data[len];

		void init_zero();
	};
	Decimal::Decimal() {
		this->init_zero();
	}
	Decimal::Decimal(double x) {
		this->init_zero();

		if (x < 0) {
			is_neg = true;
			x = -x;
		}

		integer = (long long)x;
		x -= integer;

		for (int i = 0; i < len; i++) {
			x *= mo;
			if (x < 0) x = 0;
			data[i] = (int)x;
			x -= data[i];
		}
	}
	void Decimal::init_zero() {
		is_neg = false;
		integer = 0;
		memset(data, 0, len * sizeof(int));
	}
	void Decimal::append_to_string(std::string &s, long long x) {
		if (x == 0) {
			s.append(1, 48);
			return;
		}

		static char _[0x7ffffff];
		int cnt = 0;
		while (x) {
			_[cnt++] = x % 10;
			x /= 10;
		}
		while (cnt--) {
			s.append(1, _[cnt] + 48);
		}
	}
	std::string Decimal::to_string(int p) const {
		std::string ret;

		if (is_neg && !this->is_zero()) {
			ret = "-";
		}

		append_to_string(ret, this->integer);

		ret.append(1, '.');

		for (int i = 0; i < len; i++) {
			// append data[i] as "%09d"
			int x = mo / 10;
			int tmp = data[i];
			while (x) {
				ret.append(1, 48 + tmp / x);
				tmp %= x;
				x /= 10;
				if (--p == 0) {
					break;
				}
			}
			if (p == 0) break;
		}

		if (p > 0) {
			ret.append(p, '0');
		}

		return ret;
	}
	bool operator >= (const Decimal &a, const Decimal &b) {
		if (a.is_neg != b.is_neg) {
			return !a.is_neg || (a.is_zero() && b.is_zero());
		}
		else if (!a.is_neg) {
			// a, b >= 0
			if (a.integer != b.integer) {
				return a.integer > b.integer;
			}
			for (int i = 0; i < Decimal::len; i++) {
				if (a.data[i] != b.data[i]) {
					return a.data[i] > b.data[i];
				}
			}
			return true;
		}
		else {
			// a, b <= 0
			if (a.integer != b.integer) {
				return a.integer < b.integer;
			}
			for (int i = 0; i < Decimal::len; i++) {
				if (a.data[i] != b.data[i]) {
					return a.data[i] < b.data[i];
				}
			}
			return true;
		}
	}
	bool operator == (const Decimal &a, const Decimal &b) {
		if (a.is_zero() && b.is_zero()) return true;
		if (a.is_neg != b.is_neg) return false;
		if (a.integer != b.integer) return false;
		for (int i = 0; i < Decimal::len; i++) {
			if (a.data[i] != b.data[i]) return false;
		}
		return true;
	}
	Decimal & Decimal::operator += (long long x) {
		if (!is_neg) {
			if (integer + x >= 0) {
				integer += x;
			}
			else {
				bool last = false;
				for (int i = len - 1; i >= 0; i--) {
					if (last || data[i]) {
						data[i] = mo - data[i] - last;
						last = true;
					}
					else {
						last = false;
					}
				}
				integer = -x - integer - last;
				is_neg = true;
			}
		}
		else {
			if (integer - x >= 0) {
				integer -= x;
			}
			else {
				bool last = false;
				for (int i = len - 1; i >= 0; i--) {
					if (last || data[i]) {
						data[i] = mo - data[i] - last;
						last = true;
					}
					else {
						last = false;
					}
				}
				integer = x - integer - last;
				is_neg = false;
			}
		}
		return *this;
	}
	Decimal & Decimal::operator += (int x) {
		return *this += (long long)x;
	}
	Decimal & Decimal::operator -= (int x) {
		return *this += (long long)-x;
	}
	Decimal & Decimal::operator -= (long long x) {
		return *this += -x;
	}
	Decimal & Decimal::operator /= (int x) {
		if (x < 0) {
			is_neg ^= 1;
			x = -x;
		}

		int last = integer % x;
		integer /= x;

		for (int i = 0; i < len; i++) {
			long long tmp = 1LL * last * mo + data[i];
			data[i] = tmp / x;
			last = tmp - 1LL * data[i] * x;
		}

		if (is_neg && integer == 0) {
			int i;
			for (i = 0; i < len; i++) {
				if (data[i] != 0) {
					break;
				}
			}
			if (i == len) {
				is_neg = false;
			}
		}

		return *this;
	}
	Decimal & Decimal::operator *= (int x) {
		if (x < 0) {
			is_neg ^= 1;
			x = -x;
		}
		else if (x == 0) {
			init_zero();
			return *this;
		}

		int last = 0;
		for (int i = len - 1; i >= 0; i--) {
			long long tmp = 1LL * data[i] * x + last;
			last = tmp / mo;
			data[i] = tmp - 1LL * last * mo;
		}
		integer = integer * x + last;

		return *this;
	}
	Decimal operator - (const Decimal &a) {
		Decimal ret = a;
		// -0 = 0
		if (!ret.is_neg && ret.integer == 0) {
			int i;
			for (i = 0; i < Decimal::len; i++) {
				if (ret.data[i] != 0) break;
			}
			if (i < Decimal::len) {
				ret.is_neg = true;
			}
		}
		else {
			ret.is_neg ^= 1;
		}
		return ret;
	}
	Decimal operator + (const Decimal &a, int x) {
		Decimal ret = a;
		return ret += x;
	}
	Decimal operator + (int x, const Decimal &a) {
		Decimal ret = a;
		return ret += x;
	}
	Decimal operator + (const Decimal &a, long long x) {
		Decimal ret = a;
		return ret += x;
	}
	Decimal operator + (long long x, const Decimal &a) {
		Decimal ret = a;
		return ret += x;
	}
	Decimal operator - (const Decimal &a, int x) {
		Decimal ret = a;
		return ret -= x;
	}
	Decimal operator - (int x, const Decimal &a) {
		return -(a - x);
	}
	Decimal operator - (const Decimal &a, long long x) {
		Decimal ret = a;
		return ret -= x;
	}
	Decimal operator - (long long x, const Decimal &a) {
		return -(a - x);
	}
	Decimal operator * (Decimal a, int x) {
		return a *= x;
	}
	Decimal operator / (const Decimal &a, int x) {
		Decimal ret = a;
		return ret /= x;
	}
	Decimal operator + (const Decimal &a, const Decimal &b) {
		if (a.is_neg == b.is_neg) {
			Decimal ret = a;
			bool last = false;
			for (int i = Decimal::len - 1; i >= 0; i--) {
				ret.data[i] += b.data[i] + last;
				if (ret.data[i] >= Decimal::mo) {
					ret.data[i] -= Decimal::mo;
					last = true;
				}
				else {
					last = false;
				}
			}
			ret.integer += b.integer + last;
			return ret;
		}
		else if (!a.is_neg) {
			// a - |b|
			return a - -b;
		}
		else {
			// b - |a|
			return b - -a;
		}
	}
	Decimal operator - (const Decimal &a, const Decimal &b) {
		if (!a.is_neg && !b.is_neg) {
			if (a >= b) {
				Decimal ret = a;
				bool last = false;
				for (int i = Decimal::len - 1; i >= 0; i--) {
					ret.data[i] -= b.data[i] + last;
					if (ret.data[i] < 0) {
						ret.data[i] += Decimal::mo;
						last = true;
					}
					else {
						last = false;
					}
				}
				ret.integer -= b.integer + last;
				return ret;
			}
			else {
				Decimal ret = b;
				bool last = false;
				for (int i = Decimal::len - 1; i >= 0; i--) {
					ret.data[i] -= a.data[i] + last;
					if (ret.data[i] < 0) {
						ret.data[i] += Decimal::mo;
						last = true;
					}
					else {
						last = false;
					}
				}
				ret.integer -= a.integer + last;
				ret.is_neg = true;
				return ret;
			}
		}
		else if (a.is_neg && b.is_neg) {
			// a - b = (-b) - (-a)
			return -b - -a;
		}
		else if (a.is_neg) {
			// -|a| - b
			return -(-a + b);
		}
		else {
			// a - -|b|
			return a + -b;
		}
	}
	Decimal & Decimal::operator += (const Decimal &b) {
		*this = *this + b;
		return *this;
	}
	Decimal & Decimal::operator -= (const Decimal &b) {
		*this = *this - b;
		return *this;
	}
}
using namespace MultiPrecision;
const unsigned long long precision = ULLONG_MAX;
const int parts = 50;
const unsigned long long part = precision / parts;
Decimal pi[parts], temp;
void Print(const char *a, int len) {
	for (int i = 0; i < len; i++)
		putchar(a[i]);
}
void print(unsigned long long l) {
	static string a;
	temp = 0;
	for (int i = 0; i < parts; i++)
		temp += pi[i];
	a = (temp * 4).to_string(10000).c_str();
	Print(a.c_str(), l);
	putchar('\n');
}
	bool ifPrint; int plen;//强行线程锁Orz
void cal(int from, int to, int serial) {
	for (unsigned long long i = from; i <= to; i += 4) {
		while (ifPrint);
		pi[serial] += (Decimal(1.0) / i);
		pi[serial] -= (Decimal(1.0) / (2.0 + i));
	}
}
		thread c[parts];
int main() {
	ios::sync_with_stdio(false);
	for (int i = 0; i < parts; i++)
		c[i]= thread(cal, i*part + 1, (i + 1)*part, i);
	while (cin >> plen) {
		ifPrint = true;
		thread a(print, plen);
		a.join();
		ifPrint = false;
	}
	temp = 0;
	for (int i = 0; i < parts; i++)c[i].join();
	for (int i = 0; i < parts; i++)temp += pi[i];
	cout << (temp*4.0).to_string(100000);
}
