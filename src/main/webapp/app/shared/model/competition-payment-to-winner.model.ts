import dayjs from 'dayjs';
import { TransactionStatus } from 'app/shared/model/enumerations/transaction-status.model';

export interface ICompetitionPaymentToWinner {
  id?: number;
  prizeTitle?: string | null;
  prizeAmount?: number | null;
  tdsAmount?: number | null;
  tdsCertificateUrl?: number | null;
  otherDeductionAmount?: number | null;
  deductionReason?: string | null;
  deductionJsonData?: string | null;
  deductionCertificateUrl?: number | null;
  paidAmount?: number | null;
  transactionId?: string | null;
  transactionScreenshotUrl?: string | null;
  transactionDate?: dayjs.Dayjs | null;
  transactionStatus?: keyof typeof TransactionStatus | null;
  paidBy?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ICompetitionPaymentToWinner> = {
  isActive: false,
};
