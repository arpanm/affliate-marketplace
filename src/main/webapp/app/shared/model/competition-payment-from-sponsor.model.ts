import dayjs from 'dayjs';
import { ICompetition } from 'app/shared/model/competition.model';
import { TransactionStatus } from 'app/shared/model/enumerations/transaction-status.model';

export interface ICompetitionPaymentFromSponsor {
  id?: number;
  amount?: number | null;
  transactionId?: string | null;
  transactionScreenshotUrl?: string | null;
  transactionDate?: dayjs.Dayjs | null;
  transactionStatus?: keyof typeof TransactionStatus | null;
  paidBy?: string | null;
  paymentReceiptUrl?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  competition?: ICompetition | null;
}

export const defaultValue: Readonly<ICompetitionPaymentFromSponsor> = {
  isActive: false,
};
